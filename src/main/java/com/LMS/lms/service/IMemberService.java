package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public interface IMemberService {
	
	public Member memberLogin(String memberMailId, String password);
	public List<Issues> getIssues(String memberMailId);
	public List<Issues> getIssuesByBookName(String memberMailId, String bookName);
	public List<Issues> getIssuesByBookAuthor(String memberMailId,String bookAuthor);
	public float getPenalty(String memberMailId);

}
