package com.LMS.lms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.lms.model.RequestIssue;

public class RequestIssueMapper implements RowMapper<RequestIssue>{
	
	@Override
	public RequestIssue mapRow(ResultSet rs, int rowNum) throws SQLException{
		RequestIssue req = new RequestIssue();
		
		req.setMemberMailId(rs.getString("member_mail_id"));
		req.setBookName(rs.getString("book_name"));
		req.setBookAuthor(rs.getString("book_author"));
		
		return req;
	}

}
