package com.LMS.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.mapper.BookMapper;
import com.LMS.lms.mapper.IssueMapper;
import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;

public class BookDaoImpl implements IBookDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean addBook(Books book) throws Exception {
		String sql = "insert into books values(?,?,?,?,?,?)";
		int rowChange = jdbcTemplate.update(sql, book.getBookName(), book.getBookAuthor(), book.getCategory(), book.getTotalBooks(), book.getAvailableBooks(), book.getLanguage());
		
		if(rowChange < 1) {
			String s = "UPDATE books SET available_books=available_books+"+book.getTotalBooks()+", total_books=total_books+"+book.getTotalBooks()+" where book_name='"+book.getBookName()+"' and book_author='"+book.getBookAuthor()+"'";
			int updated = jdbcTemplate.update(s);
			
			if(updated < 1) {
				throw new Exception();
			}
		}
		
		return true;
	}

	@Override
	public List<Issues> viewBookIssueHistory(String bookName, String bookAuthor) throws NoRecordsFoundException {
		String sql = "select * from issues where book_name like '%"+bookName+"%' and book_author like '%"+bookAuthor+"%'";
		List<Issues> issueHistory = jdbcTemplate.query(sql, new IssueMapper());
		
		if(issueHistory.isEmpty()) {
			throw new NoRecordsFoundException();
		}
		
		return issueHistory;
	}

	@Override
	public boolean removeBookFromLibrary(String bookName, String bookAuthor) throws BookNotFoundException {
		String sql = "delete from books where book_name like'%"+bookName+"%' and book_author like'%"+bookAuthor+"%'";
		
		int deletedRow = jdbcTemplate.update(sql);
		
		if(deletedRow < 1) {
			throw new BookNotFoundException();
		}
		
		return true;
		
	}

	@Override
	public List<Books> searchBookByName(String bookName) throws BookNotFoundException {
		String sql = "select * from books where book_name like '%"+bookName+"%'";
		List<Books> bookList = jdbcTemplate.query(sql, new BookMapper());
		
		if(bookList.isEmpty()) {
			throw new BookNotFoundException();
		}
		
		return bookList;
	}

	@Override
	public List<Books> searchBookByAuthor(String bookAuthor) throws BookNotFoundException {
		String sql = "select * from books where book_author like '%"+bookAuthor+"%'";
		List<Books> bookList = jdbcTemplate.query(sql, new BookMapper());
		
		if(bookList.isEmpty()) {
			throw new BookNotFoundException();
		}
		
		return bookList;
	}

	@Override
	public List<Books> searchBookByCategory(String category) throws BookNotFoundException {
		String sql = "select * from books where category like '%"+category+"%'";
		List<Books> bookList = jdbcTemplate.query(sql, new BookMapper());
		
		if(bookList.isEmpty()) {
			throw new BookNotFoundException();
		}
		
		return bookList;
	}

	@Override
	public List<Books> viewAllBooks() throws Exception{
		String sql = "select * from books";
		List<Books> bookList = jdbcTemplate.query(sql, new BookMapper());
		
		return bookList;
	}

}
