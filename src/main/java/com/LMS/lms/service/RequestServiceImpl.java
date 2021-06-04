package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.model.Request;

public class RequestServiceImpl implements IRequestService {

	@Override
	public List<Request> adminViewRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adminRemovingRequest(String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRequest(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Request> viewRequest(String memberMailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeRequest(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return false;
	}

}
