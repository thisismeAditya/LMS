package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.lms.dao.IMemberDao;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	IMemberDao memberDao;

	/**
	 * This method lets members login.This may throw 2 types of exception
	 * 1.If the entered mail-id does not exist in the member table
	 * 2.If the password is wrong
	 *@param memberMailId
	 *@param password
	 *@return member : returns the requested member if mail-id and password are correct
	 */
	@Override
	public Member memberLogin(String memberMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException {
		return memberDao.memberLogin(memberMailId, password);
	}

	/**
	 * This method returns all the issues made by the member
	 *@param memberMailId
	 *@return List<Issues> : all the issues made by the member
	 */
	@Override
	public List<Issues> getIssues(String memberMailId) throws Exception {
		return memberDao.getIssuesByMemberMailId(memberMailId);
	}

	/**
	 * This method searches the issues that the member has made whose book name matches the entered book name 
	 *@param memberMaiId
	 *@param bookName
	 *@return List<Issues>: list of all the issues made by the member that match the entered bookName
	 */
	@Override
	public List<Issues> getIssuesByBookName(String memberMailId, String bookName) throws Exception {
		return memberDao.getIssuesByMemberMailIdandBookName(memberMailId, bookName);
	}
	
	/**
	 * This method searches the issues that the member has made whose book author matches the entered book author
	 *@param memberMaiId
	 *@param bookAuthor
	 *@return List<Issues>: list of all the issues made by the member that match the entered book author
	 */
	@Override
	public List<Issues> getIssuesByBookAuthor(String memberMailId, String bookAuthor) throws Exception {
		return memberDao.getIssuesByMemberMailIdandBookAuthor(memberMailId, bookAuthor);
	}

	/**
	 * This method returns the total penalty due for the member 
	 *@param memberMailId
	 *@return float: the total penalty due for that member
	 */
	@Override
	public float getPenalty(String memberMailId) throws Exception {
		return memberDao.getPenatlyByMemberId(memberMailId);
	}

}
