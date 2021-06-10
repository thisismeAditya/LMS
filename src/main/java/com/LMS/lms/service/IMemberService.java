package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public interface IMemberService {
	
	public Member memberLogin(String memberMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException;
	public List<Issues> getIssues(String memberMailId) throws Exception;
	public List<Issues> getIssuesByBookName(String memberMailId, String bookName) throws Exception;
	public List<Issues> getIssuesByBookAuthor(String memberMailId,String bookAuthor) throws Exception;
	public float getPenalty(String memberMailId) throws Exception;
	public Member getMemberByMailId(String memberMailId);

}
