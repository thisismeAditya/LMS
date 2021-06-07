package com.LMS.lms.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.LMS.lms.exception.AllAvailableBooksIssuedException;
import com.LMS.lms.exception.BookAlreadyDeletedException;
import com.LMS.lms.exception.BookAlreadyExistsInLibraryException;
import com.LMS.lms.exception.BookAlreadyRequestedException;
import com.LMS.lms.exception.BookAlreadyReturnedException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.IssueIdNotFoundException;
import com.LMS.lms.exception.NoIssueFoundForMemberMailIdException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.RequestForIssueNotFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {
	
	/**
	 * This is a general exception handler for handling unexpected errors
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	   public ResponseEntity<Object> handleException(Exception exception) {
	      return new ResponseEntity<>("Internal Error! Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * This method handles exception when member tries to add a request for a book that already exists in the library
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = BookAlreadyExistsInLibraryException.class)
	public ResponseEntity<Object> handleBookAlreadyExistsInLibraryException(BookAlreadyExistsInLibraryException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
		
		//409 Conflict
		
		return new ResponseEntity<>("Book Already exists in library!", HttpStatus.CONFLICT);
	}
	
	/**
	 * This method handles exception thrown whenever the requested book is not found
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception){
		//HttpStatus.NOT_FOUND -> The server has not found anything matching the Request-URI.
		//404 Not Found
		
		return new ResponseEntity<Object>("No such book Found! Please check for spelling and try again, or, contact Admin!", HttpStatus.NOT_FOUND);
	}
	
	/**
	 * This method handles exception thrown when an existing member mail-id is trying to get registered
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Email-Id already taken! Enter a unique Email ID!", HttpStatus.CONFLICT);
	}
	
	/**
	 * This method handles exception thrown whenever there are no records found from the executed query
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = NoRecordsFoundException.class)
	   public ResponseEntity<Object> handleNoRecordsFoundException(NoRecordsFoundException exception) {
		
		//HttpStatus.NOT_FOUND -> The server has not found anything matching the Request-URI.
		
		//404 Not Found
	      return new ResponseEntity<>("No records found!", HttpStatus.NOT_FOUND);
	   }
	
	/**
	 * This method handles exception whenever a login is made with a wrong password
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = UserIdPasswordMismatchException.class)
	   public ResponseEntity<Object> handleUserIdPasswordMismatchException(UserIdPasswordMismatchException exception) {
		//HttpStatus.UNAUTHORIZED -> The request requires user authentication. 
		//401 Unauthorized
		
	      return new ResponseEntity<>("Wrong password! Please try again!", HttpStatus.UNAUTHORIZED);
	   }
	
	/**
	 * This method handles exception whenever searched user is not found
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = UserNotFoundException.class)
	   public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
		//HttpStatus.UNAUTHORIZED -> The request requires user authentication. 
		//401 Unauthorized
		
	      return new ResponseEntity<>("No such user found! Please enter correct credentials!", HttpStatus.UNAUTHORIZED);
	   }
	
	/**
	 * This method handles exception whenever a duplicate primary key is being added to the table
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = DuplicateKeyException.class)
	public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Already exists in the system!", HttpStatus.CONFLICT);
	}
	
	/**
	 * This method handles exception when member tries to add a book to requests when the requested book already exists in the library
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = BookAlreadyRequestedException.class)
	public ResponseEntity<Object> handleBookAlreadyRequestedException(BookAlreadyRequestedException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Book already requested!", HttpStatus.CONFLICT);
	}
	
	/**
	 * This method handles exception when Http message is not understandable
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	   public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
	      return new ResponseEntity<>("No data Entered!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * This method handles exception when no handler is found
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	   public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception) {
	      return new ResponseEntity<>("Error encountered!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }

	/**
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = ConfigDataResourceNotFoundException.class)
	   public ResponseEntity<Object> handleConfigDataResourceNotFoundException(ConfigDataResourceNotFoundException exception) {
	      return new ResponseEntity<>("Error encountered!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * This method handles exception when admin tries to delete a book that does not exist in the library
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = BookAlreadyDeletedException.class)
	   public ResponseEntity<Object> handleBookAlreadyDeletedException(BookAlreadyDeletedException exception) {
	      return new ResponseEntity<>("Book Already Deleted!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * This method handles exception when no. of available books =0, so those books cannot be issued
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = AllAvailableBooksIssuedException.class)
	   public ResponseEntity<Object> handleAllAvailableBooksIssuedException(AllAvailableBooksIssuedException exception) {
	      return new ResponseEntity<>("All Available books are Issued! No Books Available.", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * Members can only issue a book after 'requesting for issue'. If 'request for issue' does not exist , this method handles the exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = RequestForIssueNotFoundException.class)
	   public ResponseEntity<Object> handleRequestForIssueNotFoundException(RequestForIssueNotFoundException exception) {
	      return new ResponseEntity<>("Could not issue the book because Request For Issue not found!!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * When admin tries to close or renew an issue that has already been closed, this method handles that exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = BookAlreadyReturnedException.class)
	   public ResponseEntity<Object> handleBookAlreadyReturnedException(BookAlreadyReturnedException exception) {
	      return new ResponseEntity<>("Issue has already been closed !!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * This method handles the exception when required issue-id is not found in the issues table 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = IssueIdNotFoundException.class)
	   public ResponseEntity<Object> handleIssueIdNotFoundException(IssueIdNotFoundException exception) {
	      return new ResponseEntity<>("No issue with the entered Issue-Id found !!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	/**
	 * This method handles exception when there is no issue-id for the corresponding member mail-id
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = NoIssueFoundForMemberMailIdException.class)
	   public ResponseEntity<Object> handleNoIssueFoundForMemberMailIdException(NoIssueFoundForMemberMailIdException exception) {
	      return new ResponseEntity<>("Member Email-id and Issue-Id do not match!!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
}
