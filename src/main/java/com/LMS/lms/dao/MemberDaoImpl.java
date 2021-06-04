package com.LMS.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
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
	public List<Issues> getIssuesByMemberMailId(String memberMailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issues> getIssuesByMemberMailIdandBookName(String memberMailId, String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issues> getIssuesByMemberMailIdandBookAuthor(String memberMailId, String bookAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getPenatlyByMemberId(String memberMailId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
