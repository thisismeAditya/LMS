package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.LMS.lms.dao.IBookDao;
import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;

public class BookServiceImpl implements IBookService {
	
	@Autowired
	IBookDao bookDao;

	@Override
	public boolean addBook(Books book) {
		// TODO Auto-generated method stub
		return bookDao.addBook(book);
	}

	@Override
	public List<Issues> viewBookIssueHistory(String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return bookDao.viewBookIssueHistory(bookName, bookAuthor);
	}

	@Override
	public boolean removeBookFromLibrary(String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return bookDao.removeBookFromLibrary(bookName, bookAuthor);
	}

	@Override
	public List<Books> searchBookByName(String bookName) {
		// TODO Auto-generated method stub
		return bookDao.searchBookByName(bookName);
	}

	@Override
	public List<Books> searchBookByAuthor(String bookAuthor) {
		// TODO Auto-generated method stub
		return bookDao.searchBookByAuthor(bookAuthor);
	}

	@Override
	public List<Books> searchBookByCategory(String category) {
		// TODO Auto-generated method stub
		return bookDao.searchBookByCategory(category);
	}

	@Override
	public List<Books> viewAllBooks() {
		// TODO Auto-generated method stub
		return bookDao.viewAllBooks();
	}

}
