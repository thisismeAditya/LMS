package com.LMS.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="members")
public class Member {
	
	@Id
	@Column(name="member_mail_id", length=30)
	private String memberMailId;
	
	@Column(name="member_name", length=30)
	private String memberName;
	
	@Column(name="member_password", length=30)
	private String memberPassword;
	
	@Column(name="admin_mail_id", length=30)
	private String adminMailId;
	
	public String getMemberMailId() {
		return memberMailId;
	}
	public void setMemberMailId(String memberMailId) {
		this.memberMailId = memberMailId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getAdminMailId() {
		return adminMailId;
	}
	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}
	
	public Member(String memberMailId, String memberName, String memberPassword, String adminMailId) {
		super();
		this.memberMailId = memberMailId;
		this.memberName = memberName;
		this.memberPassword = memberPassword;
		this.adminMailId = adminMailId;
	}
	
	public Member() {
		super();
	}
	
}
