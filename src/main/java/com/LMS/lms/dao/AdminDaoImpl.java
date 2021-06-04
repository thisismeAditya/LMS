package com.LMS.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.mapper.AdminEmailRowMapper;
import com.LMS.lms.mapper.IssueMapper;
import com.LMS.lms.model.Admin;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public class AdminDaoImpl implements IAdminDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MemberDaoImpl memberDao;

	@Override
	public Admin login(String adminMailId, String password) throws UserIdPasswordMismatchException, UserNotFoundException {
		
		Admin admin = checkIfEmailExists(adminMailId);
		
		if(admin != null) {
			if(admin.getPassword().equals(password)) {
				return admin;
			}else {
				throw new UserIdPasswordMismatchException();
			}
		}
		
		throw new UserNotFoundException();
	}

	private Admin checkIfEmailExists(String adminMailId){
		String sql = "select admin_mail_id, admin_name, admin_password from admin where admin_mail_id='"+adminMailId+"'";
		Admin admin = jdbcTemplate.queryForObject(sql, new AdminEmailRowMapper());
		
		return admin;
	}

	@Override
	public boolean addnewMember(Member member) throws EmailAlreadyExistsException {
		// TODO Auto-generated method stub
		
		String sql = "insert into members values(?,?,?,?)";
		int rowChange = jdbcTemplate.update(sql, member.getMemberMailId(), member.getMemberName(), member.getMemberPassword(), member.getAdminMailId());
		
		if(rowChange < 1) {
			throw new EmailAlreadyExistsException();
		}
		
		return true;
	}

	@Override
	public List<Issues> getMemberIssueHistory(String memberMailId) throws NoRecordsFoundException, UserNotFoundException {
		Member member = memberDao.checkMemberEmail(memberMailId);
		
		if(member == null) {
			throw new UserNotFoundException();
		}
		
		String sql = "select * from issues where member_mail_id='"+memberMailId+"'";
		List<Issues> issueHistory = jdbcTemplate.query(sql, new IssueMapper());
		
		if(issueHistory.isEmpty()) {
			throw new NoRecordsFoundException();
		}
		
		return issueHistory;
	}

	@Override
	public boolean deleteMemberByMemberId(String memberMailId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from members where member_mail_id='"+memberMailId+"'";
		
		int rowChange = jdbcTemplate.update(sql);
		
		if(rowChange < 1) {
			throw new Exception();
		}
		
		return true;
	}

}
