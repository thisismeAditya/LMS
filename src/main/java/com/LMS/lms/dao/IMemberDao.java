package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public interface IMemberDao {

	public Member memberLogin(String memberMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException;

	public List<Issues> getIssuesByMemberMailId(String memberMailId) throws Exception;

	public List<Issues> getIssuesByMemberMailIdandBookName(String memberMailId, String bookName) throws Exception;

	public List<Issues> getIssuesByMemberMailIdandBookAuthor(String memberMailId, String bookAuthor) throws Exception;

	public float getPenatlyByMemberId(String memberMailId) throws Exception;

}
