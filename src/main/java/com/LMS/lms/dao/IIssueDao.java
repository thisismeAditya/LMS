package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public interface IIssueDao {

	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor);

	public boolean addIssues(int bookId, String memberMailId, String adminMailId);

	public Issues closingIssue(int issueId);

	public Issues renewingIssues(int issueId);

	public List<RequestIssue> viewAllRequest();

	public boolean addRequestToIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId);

	public boolean removeRequestFromRequestIssue(String memberMailId, String bookName, String bookAuthor);

}
