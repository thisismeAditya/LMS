package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.exception.BookAlreadyReturnedException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.IssueIdNotFoundException;
import com.LMS.lms.exception.NoIssueFoundForMemberMailIdException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public interface IIssueService {
	
	public Issues closingIssues(int issueId) throws BookNotFoundException, BookAlreadyReturnedException, IssueIdNotFoundException;
	public Issues renewingIssues(int issueId) throws BookNotFoundException, IssueIdNotFoundException, BookAlreadyReturnedException;
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor);
	public List<RequestIssue> viewAllRequest() throws Exception;
	public boolean confirmRequestForIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception;
	public boolean removeRequestForIssue(String memberMailId, String bookName, String bookAuthor) throws Exception;
	public boolean updatePenalties();
	public float settlePenalties(String memberMailId, int issueId) throws NoIssueFoundForMemberMailIdException;

}
