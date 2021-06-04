package com.LMS.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.mapper.IssueMapper;
import com.LMS.lms.mapper.MemberMapper;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public class MemberDaoImpl implements IMemberDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Member memberLogin(String memberMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException {
		
		Member member = checkMemberEmail(memberMailId);
		
		if(member != null) {
			if(member.getMemberPassword().equals(password)) {
				return member;
			}
			throw new UserIdPasswordMismatchException();
		}
		throw new UserNotFoundException();
		
	}

	public Member checkMemberEmail(String memberMailId) {
		String sql = "select * from members where member_mail_id='"+memberMailId+"'";
		Member member = jdbcTemplate.queryForObject(sql, new MemberMapper());
		
		return member;
	}

	@Override
	public List<Issues> getIssuesByMemberMailId(String memberMailId) throws Exception{
		String sql = "select * from issues where member_mail_id='"+memberMailId+"'";
		List<Issues> issueList = jdbcTemplate.query(sql, new IssueMapper());
		
		return issueList;
	}

	@Override
	public List<Issues> getIssuesByMemberMailIdandBookName(String memberMailId, String bookName) throws Exception{
		String sql = "select * from issues where member_mail_id='"+memberMailId+"' and book_name='"+bookName+"'";
		List<Issues> issueListByBookName = jdbcTemplate.query(sql, new IssueMapper());
		
		return issueListByBookName;
	}

	@Override
	public List<Issues> getIssuesByMemberMailIdandBookAuthor(String memberMailId, String bookAuthor) throws Exception{
		String sql = "select * from issues where member_mail_id='"+memberMailId+"' and book_author='"+bookAuthor+"'";
		List<Issues> issueListByBookAuthor = jdbcTemplate.query(sql, new IssueMapper());
		
		return issueListByBookAuthor;
	}

	@Override
	public float getPenatlyByMemberId(String memberMailId) throws Exception{
		String sql = "select sum(penalty) from issues where member_mail_id='"+memberMailId+"'";
		
		Float totalPenalty = jdbcTemplate.queryForObject(sql, Float.class);
		
		return totalPenalty;
	}

}
