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
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	IRequestService requestService;
	
	@Autowired
	IBookService bookService;
	
	@Autowired
	IIssueService issueService;
	
	/**
	 * This method executes member sign in.
	 * If member mail-id not found, throws exception
	 * If member mail-id is already registered but the password does not match -> throws exception
	 * Returns the member object if correct mail-id and password are found 
	 * @param memberMailId
	 * @param password
	 * @return
	 * @throws UserIdPasswordMismatchException
	 * @throws UserNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/memberSignIn/{memberMailId}/{password}", method = RequestMethod.GET)
	public ResponseEntity<Member> memberSignIn(@PathVariable String memberMailId,@PathVariable String password) throws UserIdPasswordMismatchException, UserNotFoundException, Exception{
		return ResponseEntity.accepted().body(memberService.memberLogin(memberMailId, password));
	}
	
	/**
	 * This method all the issues made by the member
	 * @param memberMailId
	 * @return
	 * @throws BookNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/member/viewIssues/{memberMailId}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewIssues(@PathVariable String memberMailId) throws BookNotFoundException, Exception{
		return ResponseEntity.accepted().body(memberService.getIssues(memberMailId));
	}
	
	
	/**
	 * This method returns all the issues in which the book name matches the inputted book name
	 * @param memberMailId
	 * @param bookName
	 * @return
	 * @throws BookNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/member/searchIssuesByName/{memberMailId}/{bookName}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewIssuesByBookName(@PathVariable String memberMailId, @PathVariable String bookName) throws BookNotFoundException, Exception{
		return ResponseEntity.accepted().body(memberService.getIssuesByBookName(memberMailId, bookName));
	}
	
	/**
	 * This method returns all the issues whose author name matches the author name entered by the user
	 * @param memberMailId
	 * @param bookAuthor
	 * @return
	 * @throws BookNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/member/searchIssuesByAuthor/{memberMailId}/{bookAuthor}", method = RequestMethod.GET)
	public ResponseEntity<List<Issues>> viewIssuesByBookAuthor(@PathVariable String memberMailId, @PathVariable String bookAuthor) throws BookNotFoundException, Exception{
		return ResponseEntity.accepted().body(memberService.getIssuesByBookAuthor(memberMailId, bookAuthor));
	}
	
	/**
	 * This method returns the sum of all the penalties due for all the issues under the member
	 * @param memberMailId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/member/viewTotalPenalty/{memberMailId}", method = RequestMethod.GET)
	public ResponseEntity<Float> viewPenalty(@PathVariable String memberMailId) throws Exception{
		return ResponseEntity.accepted().body(memberService.getPenalty(memberMailId));
	}
	
	/**
	 * This method lets the member add a book to suggestions/request when a book does not exist in the library
	 * @param memberMailId
	 * @param bookName
	 * @param bookAuthor
	 * @return
	 * @throws BookAlreadyExistsInLibraryException
	 * @throws Exception
	 */
	@RequestMapping(value="/member/addRequestBook/{memberMailId}/{bookName}/{bookAuthor}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addBookRequest(@PathVariable String memberMailId,@PathVariable String bookName,@PathVariable String bookAuthor) throws BookAlreadyExistsInLibraryException, Exception{
		return ResponseEntity.accepted().body(requestService.addRequest(memberMailId, bookName, bookAuthor));
	}
	
	/**
	 * This method returns all the requests for books that the member made 
	 * @param requessterMailId
	 * @return
	 * @throws BookNotFoundException
	 * @throws Exception
	 */
	@RequestMapping(value="/member/viewRequests/{requessterMailId}", method = RequestMethod.GET)
	public ResponseEntity<List<Request>> viewMemberRequests(@PathVariable String requessterMailId) throws BookNotFoundException, Exception{
		return ResponseEntity.accepted().body(requestService.viewRequest(requessterMailId));
	}
	
	/**
	 * This method lets the member remove any of their request from the request table
	 * @param requesterMailId
	 * @param bookName
	 * @param bookAuthor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/member/removeRequest/{requesterMailId}/{bookName}/{bookAuthor}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeMemberRequests(@PathVariable String requesterMailId, @PathVariable String bookName, @PathVariable String bookAuthor) throws Exception{
		return ResponseEntity.accepted().body(requestService.removeRequest(requesterMailId, bookName, bookAuthor));
	}
	
	
	/**
	 * This method returns all the books that exist in the library, for member side
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/member/viewAllBooks", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> viewAllBooks() throws Exception{
		return ResponseEntity.accepted().body(bookService.viewAllBooks());
	}
	
	
	/**
	 * This method returns all the books whose name matches with the inputted book name
	 * @param bookName
	 * @return
	 * @throws BookNotFoundException
	 * @throws HttpMessageNotReadableException
	 */
	@RequestMapping(value="/member/searchByName/{bookName}", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> viewAllBooksByName(@PathVariable String bookName) throws BookNotFoundException, HttpMessageNotReadableException{
		return ResponseEntity.accepted().body(bookService.searchBookByName(bookName));
	}
	
	
	/**
	 * This method returns all the books whose author matches the inputted book author
	 * @param bookAuthor
	 * @return
	 * @throws BookNotFoundException
	 */
	@RequestMapping(value="/member/searchByAuthor/{bookAuthor}", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> viewAllBooksByAuthor(@PathVariable String bookAuthor) throws BookNotFoundException{
		return ResponseEntity.accepted().body(bookService.searchBookByAuthor(bookAuthor));
	}
	
	
	/**
	 * This method returns all the books whose category matches with the inputted category 
	 * @param category
	 * @return
	 * @throws BookNotFoundException
	 */
	@RequestMapping(value="/member/searchByCategory/{category}", method = RequestMethod.GET)
	public ResponseEntity<List<Books>> searchBookByCategory(@PathVariable String category) throws BookNotFoundException{
		return ResponseEntity.accepted().body(bookService.searchBookByCategory(category));
	}
	
	
	/**
	 * For issuing a book, member first needs to add a 'request to issue' for that book.
	 * This method adds the 'request to issue' for the given member and book 
	 * @param memberMailId
	 * @param bookName
	 * @param bookAuthor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/member/addIssueRequest/{memberMailId}/{bookName}/{bookAuthor}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addIssueRequest(@PathVariable String memberMailId,@PathVariable String bookName,@PathVariable String bookAuthor) throws Exception{
		return ResponseEntity.accepted().body(issueService.addIssueRequest(memberMailId, bookName, bookAuthor));
	}
	
}
