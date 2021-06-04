package com.LMS.lms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.lms.model.Admin;

public class AdminEmailRowMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Admin admin = new Admin();
		admin.setAdminMailId(rs.getString("admin_mail_Id"));
		admin.setAdminName(rs.getString("admin_name"));
		admin.setPassword(rs.getString("admin_password"));
		
		return admin;
	}

}
