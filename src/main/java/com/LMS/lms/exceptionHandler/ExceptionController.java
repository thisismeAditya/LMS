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
	
	@ExceptionHandler(value = Exception.class)
	   public ResponseEntity<Object> handleException(Exception exception) {
	      return new ResponseEntity<>("Internal Error! Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = BookAlreadyExistsInLibraryException.class)
	public ResponseEntity<Object> handleBookAlreadyExistsInLibraryException(BookAlreadyExistsInLibraryException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
		
		//409 Conflict
		
		return new ResponseEntity<>("Book Already exists in library!", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception){
		//HttpStatus.NOT_FOUND -> The server has not found anything matching the Request-URI.
		//404 Not Found
		
		return new ResponseEntity<Object>("No such book Found! Please check for spelling and try again, or, contact Admin!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Email-Id already taken! Enter a unique Email ID!", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = NoRecordsFoundException.class)
	   public ResponseEntity<Object> handleNoRecordsFoundException(NoRecordsFoundException exception) {
		
		//HttpStatus.NOT_FOUND -> The server has not found anything matching the Request-URI.
		
		//404 Not Found
	      return new ResponseEntity<>("No records found!", HttpStatus.NOT_FOUND);
	   }
	
	@ExceptionHandler(value = UserIdPasswordMismatchException.class)
	   public ResponseEntity<Object> handleUserIdPasswordMismatchException(UserIdPasswordMismatchException exception) {
		//HttpStatus.UNAUTHORIZED -> The request requires user authentication. 
		//401 Unauthorized
		
	      return new ResponseEntity<>("Wrong password! Please try again!", HttpStatus.UNAUTHORIZED);
	   }
	
	@ExceptionHandler(value = UserNotFoundException.class)
	   public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
		//HttpStatus.UNAUTHORIZED -> The request requires user authentication. 
		//401 Unauthorized
		
	      return new ResponseEntity<>("No such user found! Please enter correct credentials!", HttpStatus.UNAUTHORIZED);
	   }
	
	@ExceptionHandler(value = DuplicateKeyException.class)
	public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Already exists in the system!", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = BookAlreadyRequestedException.class)
	public ResponseEntity<Object> handleBookAlreadyRequestedException(BookAlreadyRequestedException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Book already requested!", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	   public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
	      return new ResponseEntity<>("No data Entered!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = NoHandlerFoundException.class)
	   public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception) {
	      return new ResponseEntity<>("Error encountered!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }

	@ExceptionHandler(value = ConfigDataResourceNotFoundException.class)
	   public ResponseEntity<Object> handleConfigDataResourceNotFoundException(ConfigDataResourceNotFoundException exception) {
	      return new ResponseEntity<>("Error encountered!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = BookAlreadyDeletedException.class)
	   public ResponseEntity<Object> handleBookAlreadyDeletedException(BookAlreadyDeletedException exception) {
	      return new ResponseEntity<>("Book Already Deleted!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = AllAvailableBooksIssuedException.class)
	   public ResponseEntity<Object> handleAllAvailableBooksIssuedException(AllAvailableBooksIssuedException exception) {
	      return new ResponseEntity<>("All Available books are Issued! No Books Available.", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = RequestForIssueNotFoundException.class)
	   public ResponseEntity<Object> handleRequestForIssueNotFoundException(RequestForIssueNotFoundException exception) {
	      return new ResponseEntity<>("Could not issue the book because Request For Issue not found!!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = BookAlreadyReturnedException.class)
	   public ResponseEntity<Object> handleBookAlreadyReturnedException(BookAlreadyReturnedException exception) {
	      return new ResponseEntity<>("Issue has already been closed !!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = IssueIdNotFoundException.class)
	   public ResponseEntity<Object> handleIssueIdNotFoundException(IssueIdNotFoundException exception) {
	      return new ResponseEntity<>("No issue with the entered Issue-Id found !!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = NoIssueFoundForMemberMailIdException.class)
	   public ResponseEntity<Object> handleNoIssueFoundForMemberMailIdException(NoIssueFoundForMemberMailIdException exception) {
	      return new ResponseEntity<>("Member Email-id and Issue-Id do not match!!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
}
