package com.LMS.lms.dao;

import java.util.List;

import java.sql.Date;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.lms.exception.AllAvailableBooksIssuedException;
import com.LMS.lms.exception.BookAlreadyRequestedException;
import com.LMS.lms.exception.BookAlreadyReturnedException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.IssueIdNotFoundException;
import com.LMS.lms.exception.NoIssueFoundForMemberMailIdException;
import com.LMS.lms.exception.RequestForIssueNotFoundException;
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
	public Issues closingIssue(int issueId) throws BookAlreadyReturnedException, IssueIdNotFoundException {
		
		try {
			String getIssue="select * from issues where issue_id="+issueId;
			Issues issue = jdbcTemplate.queryForObject(getIssue, new IssueMapper());
			
			if(issue.getReturnedOn()==null) {
				long millis=System.currentTimeMillis(); 
				java.sql.Date todayDate=new java.sql.Date(millis);  
				String setDOR="update issues set actual_date_of_return='"+todayDate+"' where issue_id="+issueId;
				int updatedIssue=jdbcTemplate.update(setDOR);
				issue.setReturnedOn(todayDate);
				
				if(updatedIssue==1) {
					String increaseAvailableBooks="update books set available_books=available_books+1 where book_name='"+issue.getBookName()+"' and book_author='"+issue.getBookAuthor()+"'";
					int updatedBook=jdbcTemplate.update(increaseAvailableBooks);
					if(updatedBook==1) {
						return issue;
					}
				}
			}else {
				throw new BookAlreadyReturnedException();
			}
			return null;
		}catch(EmptyResultDataAccessException e ) {
			throw new IssueIdNotFoundException();
		}			
	}
	
	@Override
	public Issues renewingIssues(int issueId) throws BookNotFoundException, IssueIdNotFoundException, BookAlreadyReturnedException {
		try {
			String getIssue="select * from issues where issue_id="+issueId;
			Issues issue = jdbcTemplate.queryForObject(getIssue, new IssueMapper());
			
			if(issue.getReturnedOn()==null) {
				long millis=System.currentTimeMillis(); 
				 java.sql.Date todayDate=new java.sql.Date(millis);  
				 java.sql.Date dateOfRenewdReturn = addDays(todayDate, 7);
				
				String sql = "update issues set date_of_return = '"+dateOfRenewdReturn+"' where issue_id ="+issueId;
				int update=jdbcTemplate.update(sql);
				issue.setDateOfReturn(dateOfRenewdReturn);
				
				if(update==1) {
					return issue;
				}
			}else {
				throw new BookAlreadyReturnedException();
			}
			return null;
		}catch(EmptyResultDataAccessException e ) {
			throw new IssueIdNotFoundException();
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
		 
		 try {
			 boolean deleteFromIssueRequest=removeRequestFromRequestIssue(memberMailId, bookName, bookAuthor);
			 if(deleteFromIssueRequest) {
				 String insertingToIssues = "insert into issues (member_mail_id,book_name,book_author,admin_mail_id, date_of_issue, date_of_return) values (?,?,?,?,?,?)";
				 String decreasingNoOfAvailableBooks="update books set available_books=available_books-1 where book_name='"+bookName+"' and book_author='"+bookAuthor+"'";
				 
				 int insertInIssues = jdbcTemplate.update(insertingToIssues, memberMailId, bookName, bookAuthor, adminMailId, dateOfIssue, dateOfReturn);
				 int updateInBooks = jdbcTemplate.update(decreasingNoOfAvailableBooks);
				 
				 if(insertInIssues==1 && updateInBooks==1) {
					 return true;
				 }
			 }else {
				 throw new RequestForIssueNotFoundException();
			 }
			 
		 }catch(DataIntegrityViolationException e) {
			 throw new AllAvailableBooksIssuedException();
		 }
		 return false;
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
		try {
			String sql = "select * from issues where actual_date_of_return is null";
			List<Issues> issueList = jdbcTemplate.query(sql, new IssueMapper());
			
			for (Issues issues : issueList) {
				long millis=System.currentTimeMillis(); 
				java.sql.Date todayDate=new java.sql.Date(millis);
				if(daysBetween(issues.getPenaltyLastUpdatedOn(), todayDate) < 0) {
					float penalty = PENALTY_PER_DAY * Math.abs(daysBetween(issues.getPenaltyLastUpdatedOn(), todayDate));
					
					String s = "update issues set penalty=penalty+"+penalty+", penalty_last_updated_on='"+todayDate+"'  where issue_id="+issues.getIssueId();
					jdbcTemplate.update(s);
				}
			}
		}catch(EmptyResultDataAccessException e ) {	}
		return true;
	}

	@Override
	public float settlePenalty(String memberMailId, int issueId) throws NoIssueFoundForMemberMailIdException {
		
		String settlePenalty = "update issues set penalty = 0 where issue_id="+issueId+" and member_mail_id='"+memberMailId+"'";
		int update=jdbcTemplate.update(settlePenalty);
		
		if(update<1) {
			throw new NoIssueFoundForMemberMailIdException();
		}
		
		String getPenalty = "select penalty from issues where issue_id="+issueId;
		Float newPenalty = jdbcTemplate.queryForObject(getPenalty, Float.class);
		
		return newPenalty;
	}

}
