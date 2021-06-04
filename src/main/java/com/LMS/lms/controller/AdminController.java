package com.LMS.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Admin;
import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;
import com.LMS.lms.model.Request;
import com.LMS.lms.model.RequestIssue;
import com.LMS.lms.service.IAdminService;
import com.LMS.lms.service.IBookService;
import com.LMS.lms.service.IIssueService;
import com.LMS.lms.service.IRequestService;

@CrossOrigin
@RestController
public class AdminController {
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	IBookService bookService;
	
	@Autowired
	IRequestService requestService;
	
	@Autowired
	IIssueService issueService;
	
	@RequestMapping(value="/adminLogin/{adminMailId}/{password}", method = RequestMethod.GET)
	public ResponseEntity<Admin> adminLogin(@PathVariable String adminMailId, @PathVariable String password) throws UserIdPasswordMismatchException, UserNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(adminService.adminLogin(adminMailId, password));
	}
	
	@RequestMapping(value="/admin/memberSignUp", method = RequestMethod.POST)
	public ResponseEntity<Boolean> memberSignUp(@RequestBody Member member) throws EmailAlreadyExistsException,Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(adminService.memberSignUp(member));
	}
	
	@RequestMapping(value="/admin/viewMemberIssueHistory/{memberMailId}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewMemberIssueHistory(@PathVariable String memberMailId) throws UserNotFoundException, Exception{
		//TODO: error handler show where actual date of return is null
		return ResponseEntity.accepted().body(adminService.viewMemberIssueHistory(memberMailId));
	}
	
	@RequestMapping(value ="/admin/deleteMember/{memberMailId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteMember(@PathVariable String memberMailId) throws Exception{
		return ResponseEntity.accepted().body(adminService.deleteMember(memberMailId));
	}
	
	@RequestMapping(value="/admin/addBook", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addBook(@RequestBody Books book) throws Exception{
		//TODO: error handler (handle the duplicateKeyException, that is to increase the number of total books by try catch in service class)
		return ResponseEntity.accepted().body(bookService.addBook(book));
	}
	
	@RequestMapping(value="/admin/viewBookIssueHistory/{bookName}/{bookAuthor}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewBookIssueHistory(@PathVariable String bookName, @PathVariable String bookAuthor) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.viewBookIssueHistory(bookName, bookAuthor));
	}
	
	@RequestMapping(value="/admin/removeBook/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeBook(@PathVariable String bookName, @PathVariable String bookAuthor) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.removeBookFromLibrary(bookName, bookAuthor));
	}
	
	@RequestMapping(value="/admin/viewRequest", method = RequestMethod.GET)
	public ResponseEntity<List<Request>> viewAllBookRequests() throws BookNotFoundException, Exception{
		//TODO: error handler , also show all unique(bookName, bookAuthor)
		return ResponseEntity.accepted().body(requestService.adminViewRequest());
	}
	
	@RequestMapping(value="admin/removeRequest/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeBookRequest(@PathVariable String bookName, @PathVariable String bookAuthor) throws Exception{
		//TODO: error handler , also show all unique(bookName, bookAuthor)
		return ResponseEntity.accepted().body(requestService.adminRemovingRequest(bookName, bookAuthor));
	}
	
	@RequestMapping(value="/admin/viewAllRequestForIssues", method = RequestMethod.GET)
	public ResponseEntity<List<RequestIssue>> viewAllRequestForIssue() throws NoRecordsFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(issueService.viewAllRequest());
	}
	
	@RequestMapping(value="/admin/confirmRequestForIssue/{memberMailId}/{bookName}/{bookAuthor}/{adminMailId}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> confirmRequestForIssue(@PathVariable String memberMailId, @PathVariable String bookName, @PathVariable String bookAuthor, @PathVariable String adminMailId) throws Exception{
		//TODO: error handler post for issues table and delete for request issue table
		return ResponseEntity.accepted().body(issueService.confirmRequestForIssue(memberMailId, bookName, bookAuthor, adminMailId));
	}
	
	@RequestMapping(value="/admin/deleteRequestForIssue/{memberMailId}/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeRequestForIssue(@PathVariable String memberMailId, @PathVariable String bookName, @PathVariable String bookAuthor) throws Exception{
		//TODO: error handler post for issues table and delete for request issue table
		return ResponseEntity.accepted().body(issueService.removeRequestForIssue(memberMailId, bookName, bookAuthor));
	}
	
	@RequestMapping(value="/admin/closeIssue/{issueId}", method = RequestMethod.GET)
	public ResponseEntity<Issues> closeIssue(@PathVariable int issueId) throws NoRecordsFoundException, Exception{
		//TODO: error handler show where actual date of return is null 
		return ResponseEntity.accepted().body(issueService.closingIssues(issueId));
	}
	
	@RequestMapping(value="/admin/renewIssue/{issueId}", method = RequestMethod.GET)
	public ResponseEntity<Issues> renewIssue(@PathVariable int issueId) throws NoRecordsFoundException, Exception{
		//TODO: error handler show where actual date of return is null 
		return ResponseEntity.accepted().body(issueService.renewingIssues(issueId));
	}
}
