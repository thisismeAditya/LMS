package com.LMS.lms.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="issues")
public class Issues {
	
	@Id
	@Column(name="issue_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int issueId;
	
	@Column(name="member_mail_Id", length=30)
	private String memberMailId;
	
	@Column(name="book_name", length=150)
	private String bookName;
	
	@Column(name="book_author", length=150)
	private String bookAuthor;
	
	@Column(name="admin_mail_id", length=30)
	private String adminMailId;
	
	@Column(name="date_of_issue")
	private Date dateOfIssue;
	
	@Column(name="date_of_return")
	private Date dateOfReturn;
	
	@Column(name="actual_date_of_return")
	private Date returnedOn;
	
	@Column(name="penalty")
	private float penalty;
	
	@Column(name="penalty_last_updated_on")
	private Date penaltyLastUpdatedOn;

	public Issues(int issueId, String memberMailId, String bookName, String bookAuthor, String adminMailId,
			Date dateOfIssue, Date dateOfReturn, Date returnedOn, float penalty, Date penaltyLastUpdatedOn) {
		super();
		this.issueId = issueId;
		this.memberMailId = memberMailId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.adminMailId = adminMailId;
		this.dateOfIssue = dateOfIssue;
		this.dateOfReturn = dateOfReturn;
		this.returnedOn = returnedOn;
		this.penalty = penalty;
		this.penaltyLastUpdatedOn = penaltyLastUpdatedOn;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

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

	public String getAdminMailId() {
		return adminMailId;
	}

	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public Date getReturnedOn() {
		return returnedOn;
	}

	public void setReturnedOn(Date returnedOn) {
		this.returnedOn = returnedOn;
	}

	public float getPenalty() {
		return penalty;
	}

	public void setPenalty(float penalty) {
		this.penalty = penalty;
	}

	public Date getPenaltyLastUpdatedOn() {
		return penaltyLastUpdatedOn;
	}

	public void setPenaltyLastUpdatedOn(Date penaltyLastUpdatedOn) {
		this.penaltyLastUpdatedOn = penaltyLastUpdatedOn;
	}

	public Issues() {
		super();
	}
	
	
}
