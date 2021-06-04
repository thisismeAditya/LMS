package com.LMS.lms.dao;

import java.util.List;

import java.sql.Date;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.LMS.lms.exception.BookAlreadyRequestedException;
import com.LMS.lms.mapper.IssueMapper;
import com.LMS.lms.mapper.RequestIssueMapper;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

public class IssueDaoImpl implements IIssueDao{
	
	@Autowired
	JdbcTemplate JdbcTemplate;

	@Override
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor) throws BookAlreadyRequestedException{
		String sql = "insert into requestIssues values (?,?,?)";
		int update = JdbcTemplate.update(sql, memberMailId, bookName, bookAuthor);
		
		if(update < 1) {
			throw new BookAlreadyRequestedException();
		}
		
		return true;
	}

	@Override
	//useless
	public boolean addIssues(int bookId, String memberMailId, String adminMailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Issues closingIssue(int issueId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Issues renewingIssues(int issueId) {
		long millis=System.currentTimeMillis(); 
		 java.sql.Date dateOfReturn=new java.sql.Date(millis);  
		 java.sql.Date dateOfRenewdReturn = addDays(dateOfReturn, 7);
		
		String sql = "update issues set date_of_return = "+dateOfRenewdReturn+" where issue_id ="+issueId;
		
		Issues newIssue = JdbcTemplate.queryForObject(sql, new IssueMapper());
		
		return newIssue;
	}

	@Override
	public List<RequestIssue> viewAllRequest() throws Exception{
		String sql = "select * from requestIssues";
		
		List<RequestIssue> requestIssueList = JdbcTemplate.query(sql, new RequestIssueMapper());
		
		return requestIssueList;
	}

	@Override
	public boolean addRequestToIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception {
		
		 long millis=System.currentTimeMillis(); 
		 java.sql.Date dateOfIssue=new java.sql.Date(millis);  
		 java.sql.Date dateOfReturn = addDays(dateOfIssue, 7);
		 
		 String sql = "insert into issues (member_mail_id,book_name,book_author,admin_mail_id, date_of_issue, date_of_return) values (?,?,?,?,?,?)";
		 
		 int update = JdbcTemplate.update(sql, memberMailId, bookName, bookAuthor, adminMailId, dateOfIssue, dateOfReturn);
		 
		 if(update < 1) {
			 throw new Exception();
		 }
		 
		 return true;
		
	}

	private Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
	}

	@Override
	public boolean removeRequestFromRequestIssue(String memberMailId, String bookName, String bookAuthor) throws Exception{
		String sql = "delete from requestIssues where member_mail_id=? and book_name=? and book_author=?";
		
		JdbcTemplate.update(sql, memberMailId, bookName, bookAuthor);
		
		return true;
	}

}
