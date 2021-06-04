package com.LMS.lms.dao;

import java.util.List;

import com.LMS.lms.model.Request;

public interface IRequestServiceDao {

	public List<Request> adminViewRequest();

	public boolean removeRequestsByAdmin(String bookName, String bookAuthor);

	public boolean addRequestByMember(String memberMailId, String bookName, String bookAuthor);

	public List<Request> viewRequest(String memberMailId);

	public boolean removeRequestByMember(String memberMailId, String bookName, String bookAuthor);

}
