package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;

public interface IBookDao {

	public boolean addBook(Books book) throws Exception;

	public List<Issues> viewBookIssueHistory(String bookName, String bookAuthor) throws NoRecordsFoundException;

	public boolean removeBookFromLibrary(String bookName, String bookAuthor) throws BookNotFoundException;

	public List<Books> searchBookByName(String bookName) throws BookNotFoundException;

	public List<Books> searchBookByAuthor(String bookAuthor) throws BookNotFoundException;

	public List<Books> searchBookByCategory(String category) throws BookNotFoundException;

	public List<Books> viewAllBooks() throws Exception;

}
