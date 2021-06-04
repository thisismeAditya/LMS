package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.exception.BookAlreadyExistsInLibraryException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.model.Request;

public interface IRequestServiceDao {

	public List<Request> adminViewRequest() throws NoRecordsFoundException;

	public boolean removeRequestsByAdmin(String bookName, String bookAuthor) throws Exception;

	public boolean addRequestByMember(String memberMailId, String bookName, String bookAuthor) throws BookAlreadyExistsInLibraryException, Exception;

	public List<Request> viewRequest(String memberMailId) throws NoRecordsFoundException;

	public boolean removeRequestByMember(String memberMailId, String bookName, String bookAuthor) throws Exception;

}
