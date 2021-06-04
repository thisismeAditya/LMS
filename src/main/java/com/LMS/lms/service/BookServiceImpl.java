package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;

public class BookServiceImpl implements IBookService {

	@Override
	public boolean addBook(Books book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Issues> viewBookIssueHistory(String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeBookFromLibrary(String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Books> searchBookByName(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Books> searchBookByAuthor(String bookAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Books> searchBookByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Books> viewAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

}
