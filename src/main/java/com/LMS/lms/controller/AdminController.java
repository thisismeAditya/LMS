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

import com.LMS.lms.exception.AllAvailableBooksIssuedException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.IssueIdNotFoundException;
import com.LMS.lms.exception.NoIssueFoundForMemberMailIdException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.RequestForIssueNotFoundException;
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
import com.LMS.lms.service.IMemberService;
import com.LMS.lms.service.IRequestService;

/**
 * @author Agrini Chaturvedi
 * @author Aditya Singh
 * @author Manisha Miryala
 * @author Kavin SV
 * @author Ayush Roy
 *
 */
@CrossOrigin
@RestController
public class AdminController {
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	IBookService bookService;
	
	@Autowired 
	IMemberService memberService;
	
	@Autowired
	IRequestService requestService;
	
	@Autowired
	IIssueService issueService;
	
	/**
	 * This method is used for admin login. 
	 * @param adminMailId
	 * @param password
	 * @return
	 * @throws UserIdPasswordMismatchException
	 * @throws UserNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/adminLogin/{adminMailId}/{password}", method = RequestMethod.GET)
	public ResponseEntity<Admin> adminLogin(@PathVariable String adminMailId, @PathVariable String password) throws UserIdPasswordMismatchException, UserNotFoundException, Exception{
		return ResponseEntity.accepted().body(adminService.adminLogin(adminMailId, password));
	}
	
	//----2
	/**
	 * This method is used for signing up a new member from admin side.
	 * @param member
	 * @return
	 * @throws EmailAlreadyExistsException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/memberSignUp", method = RequestMethod.POST)
	public ResponseEntity<Boolean> memberSignUp(@RequestBody Member member) throws EmailAlreadyExistsException,Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(adminService.memberSignUp(member));
	}
	
	//----3
	/**
	 * This method returns a members issue history.
	 * Member is identified by member mail-id.
	 * @param memberMailId
	 * @return
	 * @throws UserNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/viewMemberIssueHistory/{memberMailId}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewMemberIssueHistory(@PathVariable String memberMailId) throws UserNotFoundException, Exception{
		//TODO: error handler show where actual date of return is null
		return ResponseEntity.accepted().body(adminService.viewMemberIssueHistory(memberMailId));
	}
	
	//----4
	/**
	 * This method deletes a member from the admin side based on his mail-id 
	 * @param memberMailId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/admin/deleteMember/{memberMailId}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteMember(@PathVariable String memberMailId) throws Exception{
		return ResponseEntity.accepted().body(adminService.deleteMember(memberMailId));
	}
	
	//---5
	/**
	 * This method is used to add books to the library 
	 * @param book
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/addBook", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addBook(@RequestBody Books book) throws Exception{
		//TODO: error handler (handle the duplicateKeyException, that is to increase the number of total books by try catch in service class)
		System.out.println(book.getTotalBooks());
		return ResponseEntity.accepted().body(bookService.addBook(book));
	}
	
	//---6
	/**
	 * This method returns all the issues that have been made for a particular book.
	 * Book is identified by composite primary key= book name+book author
	 * @param bookName
	 * @param bookAuthor
	 * @return
	 * @throws BookNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/viewBookIssueHistory/{bookName}/{bookAuthor}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewBookIssueHistory(@PathVariable String bookName, @PathVariable String bookAuthor) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.viewBookIssueHistory(bookName, bookAuthor));
	}
	
	//---7
	/**
	 * This method removes all copies of the mentioned book from the books table
	 * @param bookName
	 * @param bookAuthor
	 * @return
	 * @throws BookNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/removeBook/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeBook(@PathVariable String bookName, @PathVariable String bookAuthor) throws BookNotFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(bookService.removeBookFromLibrary(bookName, bookAuthor));
	}
	
	//---8
	/**
	 * This method returns list of all the requests that members have made for books that do not exist currently in the library 
	 * @return
	 * @throws BookNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/viewRequest", method = RequestMethod.GET)
	public ResponseEntity<List<Request>> viewAllBookRequests() throws BookNotFoundException, Exception{
		//TODO: error handler , also show all unique(bookName, bookAuthor)
		return ResponseEntity.accepted().body(requestService.adminViewRequest());
	}
	
	//---9
	/**
	 * This method is used to remove request of a book from the request table. 
	 * Request removed from the admin side removes all the requests for the mentioned book from the requests table
	 * @param bookName
	 * @param bookAuthor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="admin/removeRequest/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeBookRequest(@PathVariable String bookName, @PathVariable String bookAuthor) throws Exception{
		//TODO: error handler , also show all unique(bookName, bookAuthor)
		return ResponseEntity.accepted().body(requestService.adminRemovingRequest(bookName, bookAuthor));
	}
	
	//---10
	/**
	 * For issuing a books, member first need to add a 'request for issue' mentioning the book name and book author for the book.
	 * This method shows all the requests for issues that members have made. 
	 * @return
	 * @throws NoRecordsFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/viewAllRequestForIssues", method = RequestMethod.GET)
	public ResponseEntity<List<RequestIssue>> viewAllRequestForIssue() throws NoRecordsFoundException, Exception{
		//TODO: error handler
		return ResponseEntity.accepted().body(issueService.viewAllRequest());
	}
	
	//---11
	/**
	 * The request for issue need to be approved by the admin in order to convert the request into an issue
	 * Thsi method confirms the issue for request 
	 * -> removes the corresponding request from the request for issue table, adds a new issue, and reduces the no. of available books 
	 * @param memberMailId
	 * @param bookName
	 * @param bookAuthor
	 * @param adminMailId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/confirmRequestForIssue/{memberMailId}/{bookName}/{bookAuthor}/{adminMailId}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> confirmRequestForIssue(@PathVariable String memberMailId, @PathVariable String bookName, @PathVariable String bookAuthor, @PathVariable String adminMailId) throws Exception{
		//TODO: error handler post for issues table and delete for request issue table
		return ResponseEntity.accepted().body(issueService.confirmRequestForIssue(memberMailId, bookName, bookAuthor, adminMailId));
	}
	
	//---12
	/**
	 * The request for issue can either be confirmed or deleted.
	 * This method deleted the request for issue made by the members
	 * @param memberMailId
	 * @param bookName
	 * @param bookAuthor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/deleteRequestForIssue/{memberMailId}/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeRequestForIssue(@PathVariable String memberMailId, @PathVariable String bookName, @PathVariable String bookAuthor) throws Exception{
		//TODO: error handler post for issues table and delete for request issue table
		return ResponseEntity.accepted().body(issueService.removeRequestForIssue(memberMailId, bookName, bookAuthor));
	}
	
	//---13
	/**
	 * This method is called to close an issue when the issue id is given
	 * -> adds a 'returned on' date in the issues table, increases the no.of available books in the books table 
	 * @param issueId
	 * @return
	 * @throws RequestForIssueNotFoundException
	 * @throws AllAvailableBooksIssuedException
	 * @throws IssueIdNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/closeIssue/{issueId}", method = RequestMethod.PUT)
	public ResponseEntity<Issues> closeIssue(@PathVariable int issueId) throws RequestForIssueNotFoundException,AllAvailableBooksIssuedException,IssueIdNotFoundException,Exception{
		//TODO: error handler show where actual date of return is null 
		return ResponseEntity.accepted().body(issueService.closingIssues(issueId));
	}
	
	//---14
	/**
	 * This method renews the issue. Assigns the 'date of return' new value -> today's date+7 days
	 * @param issueId
	 * @return
	 * @throws NoRecordsFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/renewIssue/{issueId}", method = RequestMethod.GET)
	public ResponseEntity<Issues> renewIssue(@PathVariable int issueId) throws NoRecordsFoundException, Exception{
		//TODO: error handler show where actual date of return is null 
		return ResponseEntity.accepted().body(issueService.renewingIssues(issueId));
	}
	
	//---15
	/**
	 * This method is used to update all the penalties of the issues that are not closed 
	 * @return
	 */
	@RequestMapping(value="/admin/updateAllPenalties", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateAllPenalties() {
		//TODO: error handler show where actual date of return is null 
		return ResponseEntity.accepted().body(issueService.updatePenalties());
	}
	
	//---16
	/**
	 * This method is used to settle the penalty corresponding to the given issue id.
	 * When an issue is settled, the penalty amount is set to 0.
	 * @param memberMailId
	 * @param issueId
	 * @return
	 * @throws NoIssueFoundForMemberMailIdException
	 */
	@RequestMapping(value="/admin/settlePenalties/{memberMailId}/{issueId}", method = RequestMethod.PUT)
	public ResponseEntity<Float> updateAllPenalties(@PathVariable String memberMailId, @PathVariable int issueId) throws NoIssueFoundForMemberMailIdException {
		//TODO: error handler show where actual date of return is null 
		return ResponseEntity.accepted().body(issueService.settlePenalties(memberMailId, issueId));
	}
	
	/**
	 * This method returns member by searching by memberMailId. 
	 * Returns to the admin side.
	 * @param memberMailId
	 * @return
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value="/admin/getMember/{memberMailId}", method = RequestMethod.GET)
	public ResponseEntity<Member> getMember(@PathVariable String memberMailId) throws UserNotFoundException {
		//TODO: error handler show where actual date of return is null 
		return ResponseEntity.accepted().body(memberService.getMemberByMailId(memberMailId));
	}
}
