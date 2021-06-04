package com.LMS.lms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.lms.model.Books;

public class BookMapper implements RowMapper<Books>{
	
	@Override
	public Books mapRow(ResultSet rs, int rowNum) throws SQLException{
		Books book = new Books();
		
		book.setBookName(rs.getString("book_name"));
		book.setBookAuthor(rs.getString("book_author"));
		book.setCategory(rs.getString("category"));
		book.setTotalBooks(rs.getInt("total_books"));
		book.setAvailableBooks(rs.getInt("available_books"));
		book.setLanguage(rs.getString("language"));
		
		return book;
	}

}
