package com.LMS.lms.dao;

import java.util.List;

import java.sql.Date;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.lms.exception.BookAlreadyRequestedException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.mapper.IssueMapper;
import com.LMS.lms.mapper.RequestIssueMapper;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.RequestIssue;

@Repository
public class IssueDaoImpl implements IIssueDao{
	
	static final float PENALTY_PER_DAY = 5;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean addIssueRequest(String memberMailId, String bookName, String bookAuthor) throws BookAlreadyRequestedException{
		String sql = "insert into requestIssues values (?,?,?)";
		int update = jdbcTemplate.update(sql, memberMailId, bookName, bookAuthor);
		
		if(update < 1) {
			throw new BookAlreadyRequestedException();
		}
		
		return true;
	}

	@Override
	public boolean addIssues(int bookId, String memberMailId, String adminMailId) {
		// TODO Auto-generated method stub
		//NOT TO BE IMPLEMENTTED
		return false;
	}

	@Override
	public Issues closingIssue(int issueId) throws BookNotFoundException {
		long millis=System.currentTimeMillis(); 
		java.sql.Date todayDate=new java.sql.Date(millis);  
		String sql="update issues set actual_date_of_return='"+todayDate+"' where issueid="+issueId;
		int update=jdbcTemplate.update(sql);
		if(update<1) {
			String sql2="select * from issues where issue_id="+issueId;
			Issues issue = jdbcTemplate.queryForObject(sql2, new IssueMapper());
			
			return issue;
			
		}else {
			throw new BookNotFoundException();
		}
	}
	
	@Override
	public Issues renewingIssues(int issueId) throws BookNotFoundException {
		long millis=System.currentTimeMillis(); 
		 java.sql.Date dateOfReturn=new java.sql.Date(millis);  
		 java.sql.Date dateOfRenewdReturn = addDays(dateOfReturn, 7);
		
		String sql = "update issues set date_of_return = '"+dateOfRenewdReturn+"' where issue_id ="+issueId;
		
		int update=jdbcTemplate.update(sql);
		if(update<1) {
			String sql2="select * from issues where issue_id="+issueId;
			Issues issue = jdbcTemplate.queryForObject(sql2, new IssueMapper());
			
			return issue;
			
		}else {
			throw new BookNotFoundException();
		}
	}

	@Override
	public List<RequestIssue> viewAllRequest() throws Exception{
		String sql = "select * from requestIssues";
		
		List<RequestIssue> requestIssueList = jdbcTemplate.query(sql, new RequestIssueMapper());
		
		return requestIssueList;
	}

	@Override
	public boolean addRequestToIssue(String memberMailId, String bookName, String bookAuthor, String adminMailId) throws Exception {
		
		 long millis=System.currentTimeMillis(); 
		 java.sql.Date dateOfIssue=new java.sql.Date(millis);  
		 java.sql.Date dateOfReturn = addDays(dateOfIssue, 7);
		 
		 String sql = "insert into issues (member_mail_id,book_name,book_author,admin_mail_id, date_of_issue, date_of_return) values (?,?,?,?,?,?)";
		 
		 int update = jdbcTemplate.update(sql, memberMailId, bookName, bookAuthor, adminMailId, dateOfIssue, dateOfReturn);
		 boolean deleteFromIssueRequest=removeRequestFromRequestIssue(memberMailId, bookName, bookAuthor);
		 
		 
		 if(update < 1 || deleteFromIssueRequest) {
			 throw new Exception();
		 }
		 
		 return true;
		
	}

	private static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
	}
	
	private static long daysBetween(Date one, Date two) { 
		long difference = (one.getTime()-two.getTime())/86400000; 
		return difference; 
	}

	@Override
	public boolean removeRequestFromRequestIssue(String memberMailId, String bookName, String bookAuthor) throws Exception{
		String sql = "delete from requestIssues where member_mail_id=? and book_name=? and book_author=?";
		int update=jdbcTemplate.update(sql, memberMailId, bookName, bookAuthor);
		
		return update>=1;
	}

	@Override
	public boolean updatePenaltiesInIssues() {
		String sql = "select * from issues where actual_date_of_return is null";
		List<Issues> issueList = jdbcTemplate.query(sql, new IssueMapper());
		
		for (Issues issues : issueList) {
			long millis=System.currentTimeMillis(); 
			java.sql.Date todayDate=new java.sql.Date(millis);
			if(daysBetween(issues.getDateOfReturn(), todayDate) < 0) {
				float penalty = PENALTY_PER_DAY * Math.abs(daysBetween(issues.getDateOfReturn(), todayDate));
				
				String s = "update issues set penalty="+penalty+" where issue_id="+issues.getIssueId();
				jdbcTemplate.update(s);
			}
		}
		return true;
	}

	@Override
	public float settlePenalty(String memberMailId, int issueId) {
		String sql = "update issues set penalty = 0 where issue_id="+issueId;
		jdbcTemplate.update(sql);
		
		String s = "select penalty from issues where issue_id="+issueId;
		Float newPenalty = jdbcTemplate.queryForObject(sql, Float.class);
		
		return newPenalty;
	}

}
