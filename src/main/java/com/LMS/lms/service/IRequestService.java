package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Request;

public interface IRequestService {
	
	public List<Request> adminViewRequest();
	public boolean adminRemovingRequest(String bookName, String bookAuthor);
	public boolean addRequest(String memberMailId, String bookName, String bookAuthor);
	public List<Request> viewRequest(String memberMailId);
	public boolean removeRequest(String memberMailId, String bookName, String bookAuthor);
	

}
