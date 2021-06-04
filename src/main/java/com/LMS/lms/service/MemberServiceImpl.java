package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public class MemberServiceImpl implements IMemberService {

	@Override
	public Member memberLogin(String memberMailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issues> getIssues(String memberMailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issues> getIssuesByBookName(String memberMailId, String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issues> getIssuesByBookAuthor(String memberMailId, String bookAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getPenalty(String memberMailId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
