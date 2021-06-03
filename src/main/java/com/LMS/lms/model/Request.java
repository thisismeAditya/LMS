package com.LMS.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="requests")
public class Request {
	
	@Id
	@Column(name="requester_mail_id")
	private String requestMailId;
	
	@Id
	@Column(name="book_name", length=150)
	private String bookName;
	
	@Id
	@Column(name="book_author", length=150)
	private String bookAuthor;
	
	@Enumerated(EnumType.STRING)
	private String ifAdded;
	
	public String getRequestMailId() {
		return requestMailId;
	}
	public void setRequestMailId(String requestMailId) {
		this.requestMailId = requestMailId;
	}
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
	public String getIfAdded() {
		return ifAdded;
	}
	public void setIfAdded(String ifAdded) {
		this.ifAdded = ifAdded;
	}
	
	public Request(String requestMailId, String bookName, String bookAuthor, String ifAdded) {
		super();
		this.requestMailId = requestMailId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.ifAdded = ifAdded;
	}
	
	public Request() {
		super();
	}
	
	

}
