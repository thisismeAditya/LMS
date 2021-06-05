package com.LMS.lms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.lms.model.Request;

public class RequestMapper implements RowMapper<Request>{
	
	@Override
	public Request mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Request req = new Request();
		
		req.setRequesterMailId(rs.getString("requester_mail_id"));
		req.setBookName(rs.getString("book_name"));
		req.setBookAuthor(rs.getString("book_author"));
		
		return req;
		
	}

}
