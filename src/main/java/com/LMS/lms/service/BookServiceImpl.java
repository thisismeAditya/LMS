package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.lms.dao.IBookDao;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	IBookDao bookDao;

	/**
	 *This method adds the book into the books table if the book does not exist already.
	 *If the book exists already, then it increases the number of total books
	 *@param book: takes the book which needs to be added
	 *@return boolean: indication of if the book is added or not
	 *@throw Exception
	 */
	@Override
	public boolean addBook(Books book) throws Exception {
		// TODO Auto-generated method stub
		return bookDao.addBook(book);
	}

	/**
	 *This method gets the list of all issues corresponding to the book.
	 *book is recognized by the composite primary key book name+ book author
	 *@param bookName
	 *@param bookAuthor
	 *@return List<Issues>
	 *@throws NoRecordsFoundException: if no issues found for the inputted book
	 */
	@Override
	public List<Issues> viewBookIssueHistory(String bookName, String bookAuthor) throws NoRecordsFoundException {
		return bookDao.viewBookIssueHistory(bookName, bookAuthor);
	}

	/**
	 * This method removes the inputted book. 
	 * Book is recognised by the composite primary key = book name+ book author
	 *@param bookName
	 *@param bookAuthor
	 *@return boolean: indication of if the book was removed successfully or not
	 *@throws BookNotFoundException: if the entered book details do not match to any book in the books table
	 */
	@Override
	public boolean removeBookFromLibrary(String bookName, String bookAuthor) throws BookNotFoundException {
		return bookDao.removeBookFromLibrary(bookName, bookAuthor);
	}

	/**
	 *This method returns list of books whose name matches with the entered book name
	 *@param bookName: holds the name searched by the member
	 *@return List<Books>: return the list of books
	 *@throws BookNotFoundException: if no book in books table matches the searched book name
	 */
	@Override
	public List<Books> searchBookByName(String bookName) throws BookNotFoundException {
		return bookDao.searchBookByName(bookName);
	}

	/**
	 *This method returns list of books whose author matches with the entered author name
	 *@param bookAuthor: holds the author searched by the member
	 *@return List<Books>: return the list of books
	 *@throws BookNotFoundException: if no book in books table matches the searched book author
	 */
	@Override
	public List<Books> searchBookByAuthor(String bookAuthor) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return bookDao.searchBookByAuthor(bookAuthor);
	}

	/**
	 *This method returns list of books whose name matches with the entered category
	 *@param category: holds the category searched by the member
	 *@return List<Books>: return the list of books
	 *@throws BookNotFoundException: if no book in books table matches the searched category
	 */
	@Override
	public List<Books> searchBookByCategory(String category) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return bookDao.searchBookByCategory(category);
	}

	/**
	 *This method returns list of all books
	 *@return List<Books>: return the list of books
	 *@throws BookNotFoundException: if no book in books table 
	 */
	@Override
	public List<Books> viewAllBooks() throws Exception {
		// TODO Auto-generated method stub
		return bookDao.viewAllBooks();
	}

}
