package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Admin;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;

public interface IAdminService {
	
	public Admin adminLogin(String adminMailId, String password);
	public boolean memberSignUp(Member member);
	public List<Issues> viewMember(String memberMailId);
	public boolean deleteMember(String memberMailId);

}
