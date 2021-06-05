package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public interface IIssueDao {

	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor);

	public boolean addIssues(int bookId, String memberMailId, String adminMailId);

	public Issues closingIssue(int issueId) throws BookNotFoundException;

	public Issues renewingIssues(int issueId) throws BookNotFoundException;

	public List<RequestIssue> viewAllRequest() throws Exception;

	public boolean addRequestToIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception;

	public boolean removeRequestFromRequestIssue(String memberMailId, String bookName, String bookAuthor) throws Exception;

	public boolean updatePenaltiesInIssues();

	public float settlePenalty(String memberMailId, int issueId);

}
