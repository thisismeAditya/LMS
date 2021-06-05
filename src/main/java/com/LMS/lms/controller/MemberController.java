package com.LMS.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.lms.exception.BookAlreadyExistsInLibraryException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;
import com.LMS.lms.model.Books;
import com.LMS.lms.model.Issues;
import com.LMS.lms.model.Member;
import com.LMS.lms.model.Request;
import com.LMS.lms.service.IBookService;
import com.LMS.lms.service.IIssueService;
import com.LMS.lms.service.IMemberService;
import com.LMS.lms.service.IRequestService;

@CrossOrigin
@RestController
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	IRequestService requestService;
	
	@Autowired
	IBookService bookService;
	
	@Autowired
	IIssueService issueService;
	
	@RequestMapping(value="/memberSignIn/{memberMailId}/{password}", method = RequestMethod.GET)
	public ResponseEntity<Member> memberSignIn(@PathVariable String memberMailId,@PathVariable String password) throws UserIdPasswordMismatchException, UserNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(memberService.memberLogin(memberMailId, password));
	}
	
	@RequestMapping(value="/member/viewIssues/{memberMailId}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewIssues(@PathVariable String memberMailId) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(memberService.getIssues(memberMailId));
	}
	
	@RequestMapping(value="/member/serachIssuesByName/{memberMailId}/{bookName}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewIssuesByBookName(@PathVariable String memberMailId, @PathVariable String bookName) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(memberService.getIssuesByBookName(memberMailId, bookName));
	}
	
	@RequestMapping(value="/member/serachIssuesByAuthor/{memberMailId}/{bookAuthor}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewIssuesByBookAuthor(@PathVariable String memberMailId, @PathVariable String bookAuthor) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(memberService.getIssuesByBookAuthor(memberMailId, bookAuthor));
	}
	
	@RequestMapping(value = "/member/viewTotalPenalty/{memberMailId}", method = RequestMethod.GET)
	public ResponseEntity<Float> viewPenalty(@PathVariable String memberMailId) throws Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(memberService.getPenalty(memberMailId));
	}
	
	@RequestMapping(value="/member/addRequestBook/{memberMailId}/{bookName}/{bookAuthor}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addBookRequest(@PathVariable String memberMailId,@PathVariable String bookName,@PathVariable String bookAuthor) throws BookAlreadyExistsInLibraryException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(requestService.addRequest(memberMailId, bookName, bookAuthor));
	}
	
	@RequestMapping(value="/member/viewRequests/{requessterMailId}", method = RequestMethod.GET)
	public ResponseEntity<List<Request>> viewMemberRequests(@PathVariable String requessterMailId) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(requestService.viewRequest(requessterMailId));
	}
	
	@RequestMapping(value = "/member/removeRequest/{requesterMailId}/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeMemberRequests(@PathVariable String requesterMailId, @PathVariable String bookName, @PathVariable String bookAuthor) throws Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(requestService.removeRequest(requesterMailId, bookName, bookAuthor));
	}
	
	@RequestMapping(value="/member/viewAllBooks", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> viewAllBooks() throws Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.viewAllBooks());
	}
	
	@RequestMapping(value="/member/searchByName/{bookName}", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> viewAllBooksByName(@PathVariable String bookName) throws BookNotFoundException, HttpMessageNotReadableException{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.searchBookByName(bookName));
	}
	
	@RequestMapping(value="/member/searchByAuthor/{bookAuthor}", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> viewAllBooksByAuthor(@PathVariable String bookAuthor) throws BookNotFoundException{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.searchBookByAuthor(bookAuthor));
	}
	
	@RequestMapping(value="/member/searchByCategory/{category}", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> searchBookByCategory(@PathVariable String category) throws BookNotFoundException{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.searchBookByCategory(category));
	}
	
	@RequestMapping(value="/member/addIssueRequest/{memberMailId}/{bookName}/{bookAuthor}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addIssueRequest(@PathVariable String memberMailId,@PathVariable String bookName,@PathVariable String bookAuthor) throws Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(issueService.addIssueRequest(memberMailId, bookName, bookAuthor));
	}
	
	
}
