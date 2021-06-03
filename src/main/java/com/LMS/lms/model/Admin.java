package com.LMS.lms.model;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name="admins")
public class Admin {
	
	@Id
	@Column(name="admin_mail_Id", unique=true, length=30)
	private String adminMailId;
	
	@Column(name="admin_name", length=30)
	private String adminName;
	
	@Column(name="admin_password", length=30)
	private String password;
	
	public String getAdminMailId() {
		return adminMailId;
	}
	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Admin(String adminMailId, String adminName, String password) {
		super();
		this.adminMailId = adminMailId;
		this.adminName = adminName;
		this.password = password;
	}
	
	public Admin() {
		super();
	}

}
