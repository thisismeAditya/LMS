package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.lms.dao.IAdminDao;
import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Admin;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	IAdminDao adminDao;

	@Override
	public Admin adminLogin(String adminMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException{
		// TODO Auto-generated method stub	
		return adminDao.login(adminMailId, password);
	}

	@Override
	public boolean memberSignUp(Member member) throws EmailAlreadyExistsException{
		return adminDao.addnewMember(member);
	}

	@Override
	public List<Issues> viewMemberIssueHistory(String memberMailId) throws NoRecordsFoundException, UserNotFoundException {
		// TODO Auto-generated method stub
		return adminDao.getMemberIssueHistory(memberMailId);
	}

	@Override
	public boolean deleteMember(String memberMailId) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.deleteMemberByMemberId(memberMailId);
	}

}
