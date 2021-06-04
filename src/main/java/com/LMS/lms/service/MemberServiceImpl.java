package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.LMS.lms.dao.IMemberDao;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	IMemberDao memberDao;

	@Override
	public Member memberLogin(String memberMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException {
		// TODO Auto-generated method stub
		return memberDao.memberLogin(memberMailId, password);
	}

	@Override
	public List<Issues> getIssues(String memberMailId) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.getIssuesByMemberMailId(memberMailId);
	}

	@Override
	public List<Issues> getIssuesByBookName(String memberMailId, String bookName) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.getIssuesByMemberMailIdandBookName(memberMailId, bookName);
	}

	@Override
	public List<Issues> getIssuesByBookAuthor(String memberMailId, String bookAuthor) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.getIssuesByMemberMailIdandBookAuthor(memberMailId, bookAuthor);
	}

	@Override
	public float getPenalty(String memberMailId) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.getPenatlyByMemberId(memberMailId);
	}

}
