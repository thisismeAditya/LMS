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

	/**
	 *This method performs admin login and throws 2 exception based on the input.
	 *1. If entered mail-id is not registered
	 *2. If mail id exists but the password does not match
	 *@param adminMailId 
	 *@param password : admin password
	 *@return admin : if email-id password are correct, then method returns the corresponding admin record
	 *@throws UserIdPasswordMismatchException
	 *@throws UserNotFoundException
	 */
	@Override
	public Admin adminLogin(String adminMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException{
		return adminDao.login(adminMailId, password);
	}

	/**
	 * This method takes a member object as input and attempts to add the new member to the DB.
	 * If the corresponding member mail-id is already registred, then it throws an exception.
	 * Else it adds the member to the members table, and returns the status of newly added member.
	 *@param member
	 *@return boolean: if added->true 
	 *@throws EmailAlreadyExistsException: if member mail-id is already registered, then throws this exception
	 */
	@Override
	public boolean memberSignUp(Member member) throws EmailAlreadyExistsException{
		return adminDao.addnewMember(member);
	}

	/**
	 *This method fetches the issue history corresponding to the inputed member mail-id. 
	 *@param memberMailId
	 *@return List<Issues>
	 *@throws NoRecordsFoundException: if there is no issue history corresponding to the entered member mail-id
	 *@throws UserNotFoundException: If the mail-id is not registered
	 */
	@Override
	public List<Issues> viewMemberIssueHistory(String memberMailId) throws NoRecordsFoundException, UserNotFoundException {
		return adminDao.getMemberIssueHistory(memberMailId);
	}

	/**
	 *This method deletes the entered member with member mail-id
	 *@param memberMailId
	 *@return boolean: indication of whether member deleted successfully or not 
	 *@throws Ecxeption
	 */
	@Override
	public boolean deleteMember(String memberMailId) throws Exception {
		return adminDao.deleteMemberByMemberId(memberMailId);
	}

}
