package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Admin;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public interface IAdminService {
	
	public Admin adminLogin(String adminMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException;
	public boolean memberSignUp(Member member) throws EmailAlreadyExistsException;
	public List<Issues> viewMemberIssueHistory(String memberMailId) throws NoRecordsFoundException, UserNotFoundException;
	public boolean deleteMember(String memberMailId) throws Exception;

}
