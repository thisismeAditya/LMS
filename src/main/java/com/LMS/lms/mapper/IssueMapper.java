package com.LMS.lms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.lms.model.Issues;

public class IssueMapper implements RowMapper<Issues>{
	
	@Override
	public Issues mapRow(ResultSet rs, int rowNum) throws SQLException{
		Issues issue = new Issues();
		
		issue.setIssueId(rs.getInt("issue_id"));
		issue.setMemberMailId(rs.getString("member_mail_id"));
		issue.setBookName(rs.getString("book_name"));
		issue.setBookAuthor(rs.getString("book_author"));
		issue.setAdminMailId(rs.getString("admin_mail_id"));
		issue.setDateOfIssue(rs.getDate("date_of_issue"));
		issue.setDateOfReturn(rs.getDate("date_of_return"));
		issue.setReturnedOn(rs.getDate("actual_date_of_return"));
		issue.setPenalty(rs.getFloat("penalty"));
		
		return issue;
	}

}
