package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.exception.BookAlreadyReturnedException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.IssueIdNotFoundException;
import com.LMS.lms.exception.NoIssueFoundForMemberMailIdException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public interface IIssueDao {

	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor);

	public Issues closingIssue(int issueId) throws BookNotFoundException, BookAlreadyReturnedException, IssueIdNotFoundException;

	public Issues renewingIssues(int issueId) throws BookNotFoundException, IssueIdNotFoundException, BookAlreadyReturnedException;

	public List<RequestIssue> viewAllRequest() throws Exception;

	public boolean addRequestToIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception;

	public boolean removeRequestFromRequestIssue(String memberMailId, String bookName, String bookAuthor) throws Exception;

	public boolean updatePenaltiesInIssues();

	public float settlePenalty(String memberMailId, int issueId) throws NoIssueFoundForMemberMailIdException;

}
