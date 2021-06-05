package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public interface IIssueService {
	
	public Issues closingIssues(int issueId) throws BookNotFoundException;
	public Issues renewingIssues(int issueId) throws BookNotFoundException;
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor);
	public List<RequestIssue> viewAllRequest() throws Exception;
	public boolean confirmRequestForIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception;
	public boolean removeRequestForIssue(String memberMailId, String bookName, String bookAuthor) throws Exception;
	boolean addIssues(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception;
	public boolean updatePenalties();
	public float settlePenalties(String memberMailId, int issueId);

}
