package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public class IssueServiceImpl implements IIssueService {

	@Override
	public boolean addIssues(int bookId, String memberMailId, String adminMailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Issues closingIssues(String memberMailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issues renewingIssues(String memberMailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RequestIssue> viewAllRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean confirmRequestForIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRequestForIssue(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return false;
	}

}
