package com.LMS.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="requests")
public class Request implements Serializable{
	
	@Id
	@Column(name="requester_mail_id")
	private String requesterMailId;
	
	@Id
	@Column(name="book_name", length=150)
	private String bookName;
	
	@Id
	@Column(name="book_author", length=150)
	private String bookAuthor;
	
	
	public String getRequesterMailId() {
		return requesterMailId;
	}
	public void setRequesterMailId(String requestMailId) {
		this.requesterMailId = requestMailId;
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
	
	public Request(String requesterMailId, String bookName, String bookAuthor) {
		super();
		this.requesterMailId = requesterMailId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
	}
	
	public Request() {
		super();
	}
	
	

}
