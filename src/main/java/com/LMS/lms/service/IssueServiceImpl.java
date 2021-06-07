package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.lms.dao.IIssueDao;
import com.LMS.lms.exception.BookAlreadyReturnedException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.IssueIdNotFoundException;
import com.LMS.lms.exception.NoIssueFoundForMemberMailIdException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

@Service
public class IssueServiceImpl implements IIssueService {
	
	@Autowired
	IIssueDao issueDao;


	/**
	 * This method closes an issue based on the issue id, if 
	 * 1.issue is not already closed in the issues table and 
	 * 2.if the such issue id exists 
	 *@param issueId
	 *@return Issues : returns the updated issue record
	 *@throws BookAlreadyReturnedException : If the issue is already closed
	 *@throws IssueIdNotFoundException : If no such issue id is found
	 */
	@Override
	public Issues closingIssues(int issueId) throws BookAlreadyReturnedException, IssueIdNotFoundException {
		return issueDao.closingIssue(issueId);
	}

	/**
	 * This method renews an issue based on the issue id, if 
	 * 1.issue is not already closed in the issues table and 
	 * 2.if the such issue id exists 
	 *@param issueId
	 *@return Issues : returns the updated issue record
	 *@throws BookAlreadyReturnedException : If the issue is already closed
	 *@throws IssueIdNotFoundException : If no such issue id is found
	 */
	@Override
	public Issues renewingIssues(int issueId) throws BookNotFoundException, IssueIdNotFoundException, BookAlreadyReturnedException {
		return issueDao.renewingIssues(issueId);
	}

	/**
	 * This method lets the member add an 'issue for request'
	 *@param memberMailId
	 *@param bookName
	 *@param bookAuthor
	 *@return boolean: indication of if the request for issue is successfully added
	 */
	@Override
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor) {
		return issueDao.addIssueRequest(memberMailId, bookName, bookAuthor);
	}

	/**
	 * This method returns all the requestIssues made by the members
	 *@return List<RequestIssues> : all the request issues are stored in this list
	 */
	@Override
	public List<RequestIssue> viewAllRequest() throws Exception {
		return issueDao.viewAllRequest();
	}

	/**
	 *This method lets admin confirms an 'issue for request' made by the member 
	 *@param memberMailId
	 *@param bookName
	 *@param bookAuthor
	 *@param adminMailId
	 *@return boolean: indication of whether the request is confirm or not
	 */
	@Override
	public boolean confirmRequestForIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception {
		return issueDao.addRequestToIssue(memberMailId, bookName, bookAuthor, adminMailId);
	}

	/**
	 * This method removes the given request for issue
	 *@param memberMailId 
	 *@param bookName
	 *@param bookAuthor
	 *@return boolean: indication of whether the request for issue is removed successfully
	 */
	@Override
	public boolean removeRequestForIssue(String memberMailId, String bookName, String bookAuthor) throws Exception {
		return issueDao.removeRequestFromRequestIssue(memberMailId, bookName, bookAuthor);
	}


	/**
	 * This method updates the penalties of all the issues which are open and subject to penalty 
	 *@return boolean : indication of if the penalties are updated successfully or not 
	 */
	@Override
	public boolean updatePenalties() {
		return issueDao.updatePenaltiesInIssues();
	}

	/**
	 * This method helps settle a penalty corresponding to the given issueId and memberMailId
	 *@param memberMailId
	 *@param issueId
	 *@return float: returns the updated penalty for that issueId
	 */
	@Override
	public float settlePenalties(String memberMailId, int issueId) throws NoIssueFoundForMemberMailIdException {
		return issueDao.settlePenalty(memberMailId, issueId);
	}

}
