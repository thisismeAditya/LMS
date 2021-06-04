package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;

public interface IBookDao {

	public boolean addBook(Books book);

	public List<Issues> viewBookIssueHistory(String bookName, String bookAuthor);

	public boolean removeBookFromLibrary(String bookName, String bookAuthor);

	public List<Books> searchBookByName(String bookName);

	public List<Books> searchBookByAuthor(String bookAuthor);

	public List<Books> searchBookByCategory(String category);

	public List<Books> viewAllBooks();

}
