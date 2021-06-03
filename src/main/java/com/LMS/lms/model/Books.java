package com.LMS.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Books {
	
	@Id
	@Column(name="book_name", length=150)
	private String bookName;
	
	@Id 
	@Column(name="book_author", length=150)
	private String bookAuthor;
	
	@Column(name="category")
	private String category;
	
	@Column(name="total_books")
	private int totalBooks;
	
	@Column(name="available_books")
	private int availableBooks;
	
	@Column(name="language", length=15)
	private String language;
	

	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getTotalBooks() {
		return totalBooks;
	}
	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}
	public int getAvailableBooks() {
		return availableBooks;
	}
	public void setAvailableBooks(int availableBooks) {
		this.availableBooks = availableBooks;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Books(String bookName, String bookAuthor, String category, int totalBooks, int availableBooks,
			String language) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.category = category;
		this.totalBooks = totalBooks;
		this.availableBooks = availableBooks;
		this.language = language;
	}
	
	public Books() {
		super();
	}
	
	

}
