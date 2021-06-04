package com.LMS.lms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.lms.model.Member;


public class MemberMapper implements RowMapper<Member>{
	
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
		Member member = new Member();
		
		member.setMemberMailId(rs.getString("member_mail_id"));
		member.setMemberName(rs.getString("member_name"));
		member.setMemberPassword(rs.getString("member_password"));
		member.setAdminMailId(rs.getString("admin_mail_id"));
		
		return member;
	}

}
