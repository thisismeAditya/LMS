package com.LMS.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.lms.dao.IRequestServiceDao;
import com.LMS.lms.exception.BookAlreadyExistsInLibraryException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.model.Request;

@Service
public class RequestServiceImpl implements IRequestService {
	
	@Autowired
	IRequestServiceDao requestServiceDao;

	/**
	 * This method returns all the requests made by all the members 
	 *@return List<Request>
	 */
	@Override
	public List<Request> adminViewRequest() throws NoRecordsFoundException {
		return requestServiceDao.adminViewRequest();
	}

	/**
	 * This method removes a request from admin side
	 *@param bookName
	 *@param bookAuthor
	 *@return boolean: indication of if the request has been removed from the table from the admin side
	 */
	@Override
	public boolean adminRemovingRequest(String bookName, String bookAuthor) throws Exception {
		return requestServiceDao.removeRequestsByAdmin(bookName, bookAuthor);
	}

	/**
	 * This method adds request to requests table from member side
	 *@param memberMailId
	 *@param bookName
	 *@param bookAuthor
	 *@return boolean: indication of if request is added successfully
	 */
	@Override
	public boolean addRequest(String memberMailId, String bookName, String bookAuthor) throws BookAlreadyExistsInLibraryException, Exception {
		return requestServiceDao.addRequestByMember(memberMailId, bookName, bookAuthor);
	}

	/**
	 * This method returns all the requests made by the member
	 *@param memberMailId
	 *@return List<Issue>
	 */
	@Override
	public List<Request> viewRequest(String memberMailId) throws NoRecordsFoundException {
		return requestServiceDao.viewRequest(memberMailId);
	}

	/**
	 * This method is called when request needs to be removed from the member side
	 *@param memberMailId
	 *@param bookName
	 *@param bookAuthor
	 *@return boolean: indication of if the request is removed by the member successfully
	 */
	@Override
	public boolean removeRequest(String memberMailId, String bookName, String bookAuthor) throws Exception {
		return requestServiceDao.removeRequestByMember(memberMailId, bookName, bookAuthor);
	}

}
