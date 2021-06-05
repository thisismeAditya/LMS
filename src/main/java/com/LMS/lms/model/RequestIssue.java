package com.LMS.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="requestIssues")
public class RequestIssue implements Serializable{
	
	@Id
	@Column(name="member_mail_id", length=30)
	private String memberMailId;
	
	@Id
	@Column(name="book_name",length=150)
	private String bookName;
	
	@Id
	@Column(name="book_author", length=150)
	private String bookAuthor;
	
	public String getMemberMailId() {
		return memberMailId;
	}
	public void setMemberMailId(String memberMailId) {
		this.memberMailId = memberMailId;
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
	
	public RequestIssue(String memberMailId, String bookName, String bookAuthor) {
		super();
		this.memberMailId = memberMailId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
	}
	
	public RequestIssue() {
		super();
	}
	
	

}
