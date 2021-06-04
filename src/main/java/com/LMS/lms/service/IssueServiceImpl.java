package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.LMS.lms.dao.IIssueDao;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public class IssueServiceImpl implements IIssueService {
	
	@Autowired
	IIssueDao issueDao;

	@Override
	public boolean addIssues(int bookId, String memberMailId, String adminMailId) {
		// TODO Auto-generated method stub
		return issueDao.addIssues(bookId, memberMailId, adminMailId);
	}

	@Override
	public Issues closingIssues(int issueId) {
		// TODO Auto-generated method stub
		return issueDao.closingIssue(issueId);
	}

	@Override
	public Issues renewingIssues(int issueId) {
		// TODO Auto-generated method stub
		return issueDao.renewingIssues(issueId);
	}

	@Override
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return issueDao.addIssueRequest(memberMailId, bookName, bookAuthor);
	}

	@Override
	public List<RequestIssue> viewAllRequest() {
		// TODO Auto-generated method stub
		return issueDao.viewAllRequest();
	}

	@Override
	public boolean confirmRequestForIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) {
		// TODO Auto-generated method stub
		return issueDao.addRequestToIssue(memberMailId, bookName, bookAuthor, adminMailId);
	}

	@Override
	public boolean removeRequestForIssue(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return issueDao.removeRequestFromRequestIssue(memberMailId, bookName, bookAuthor);
	}

}
