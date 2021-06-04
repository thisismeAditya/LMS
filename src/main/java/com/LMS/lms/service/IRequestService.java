package com.LMS.lms.service;

import java.util.List;

import com.LMS.lms.exception.BookAlreadyExistsInLibraryException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.model.Request;

public interface IRequestService {
	
	public List<Request> adminViewRequest() throws NoRecordsFoundException;
	public boolean adminRemovingRequest(String bookName, String bookAuthor) throws Exception;
	public boolean addRequest(String memberMailId, String bookName, String bookAuthor) throws BookAlreadyExistsInLibraryException, Exception;
	public List<Request> viewRequest(String memberMailId) throws NoRecordsFoundException;
	public boolean removeRequest(String memberMailId, String bookName, String bookAuthor) throws Exception;
	

}
