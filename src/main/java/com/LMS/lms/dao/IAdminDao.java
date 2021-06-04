package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Admin;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public interface IAdminDao {

	public Admin login(String adminMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException;

	public boolean addnewMember(Member member) throws EmailAlreadyExistsException;

	public List<Issues> getMemberIssueHistory(String memberMailId) throws NoRecordsFoundException, UserNotFoundException;

	public boolean deleteMemberByMemberId(String memberMailId) throws Exception;
	
	

}
