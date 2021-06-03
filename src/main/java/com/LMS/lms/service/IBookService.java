package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;

public interface IBookService {
	
	public boolean addBook(Books book);
	public List<Issues> viewBookIssueHistory(String bookName, String bookAuthor);
	public boolean removeBookFromLibrary(String bookName, String bookAuthor);
	public List<Books> searchBookByName(String bookName);
	public List<Books> searchBookByAuthor(String bookAuthor);
	public List<Books> searchBookByCategory(String category);
	public List<Books> viewAllBooks();

}
