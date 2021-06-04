package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public interface IIssueService {
	
	public boolean addIssues(int bookId, String memberMailId, String adminMailId);
	public Issues closingIssues(int issueId);
	public Issues renewingIssues(int issueId);
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor);
	public List<RequestIssue> viewAllRequest();
	public boolean confirmRequestForIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId);
	public boolean removeRequestForIssue(String memberMailId, String bookName, String bookAuthor);

}
