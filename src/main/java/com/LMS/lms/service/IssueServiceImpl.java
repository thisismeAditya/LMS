package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.lms.dao.IIssueDao;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

@Service
public class IssueServiceImpl implements IIssueService {
	
	@Autowired
	IIssueDao issueDao;


	@Override
	public Issues closingIssues(int issueId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return issueDao.closingIssue(issueId);
	}

	@Override
	public Issues renewingIssues(int issueId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return issueDao.renewingIssues(issueId);
	}

	@Override
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return issueDao.addIssueRequest(memberMailId, bookName, bookAuthor);
	}

	@Override
	public List<RequestIssue> viewAllRequest() throws Exception {
		// TODO Auto-generated method stub
		return issueDao.viewAllRequest();
	}

	@Override
	public boolean confirmRequestForIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception {
		// TODO Auto-generated method stub
		return issueDao.addRequestToIssue(memberMailId, bookName, bookAuthor, adminMailId);
	}

	@Override
	public boolean removeRequestForIssue(String memberMailId, String bookName, String bookAuthor) throws Exception {
		// TODO Auto-generated method stub
		return issueDao.removeRequestFromRequestIssue(memberMailId, bookName, bookAuthor);
	}

	@Override
	public boolean addIssues(String memberMailId, String bookName, String bookAuthor, String adminMailId)
			throws Exception {
		// TODO Auto-generated method stub
		return issueDao.addRequestToIssue(memberMailId, bookName, bookAuthor, adminMailId);
	}

	@Override
	public boolean updatePenalties() {
		return issueDao.updatePenaltiesInIssues();
	}

	@Override
	public float settlePenalties(String memberMailId, int issueId) {
		return issueDao.settlePenalty(memberMailId, issueId);
	}

}
