package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.LMS.lms.dao.IRequestServiceDao;
import com.LMS.lms.model.Request;

public class RequestServiceImpl implements IRequestService {
	
	@Autowired
	IRequestServiceDao requestServiceDao;

	@Override
	public List<Request> adminViewRequest() {
		// TODO Auto-generated method stub
		return requestServiceDao.adminViewRequest();
	}

	@Override
	public boolean adminRemovingRequest(String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return requestServiceDao.removeRequestsByAdmin(bookName, bookAuthor);
	}

	@Override
	public boolean addRequest(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return requestServiceDao.addRequestByMember(memberMailId, bookName, bookAuthor);
	}

	@Override
	public List<Request> viewRequest(String memberMailId) {
		// TODO Auto-generated method stub
		return requestServiceDao.viewRequest(memberMailId);
	}

	@Override
	public boolean removeRequest(String memberMailId, String bookName, String bookAuthor) {
		// TODO Auto-generated method stub
		return requestServiceDao.removeRequestByMember(memberMailId, bookName, bookAuthor);
	}

}
