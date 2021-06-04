package com.LMS.lms.exceptionHandler;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.LMS.lms.exception.BookAlreadyExistsInLibraryException;
import com.LMS.lms.exception.BookNotFoundException;
import com.LMS.lms.exception.EmailAlreadyExistsException;
import com.LMS.lms.exception.NoRecordsFoundException;
import com.LMS.lms.exception.UserIdPasswordMismatchException;
import com.LMS.lms.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(value = Exception.class)
	   public ResponseEntity<Object> exception(Exception exception) {
	      return new ResponseEntity<>("Internal Error! Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(value = BookAlreadyExistsInLibraryException.class)
	public ResponseEntity<Object> exception(BookAlreadyExistsInLibraryException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
		
		//409 Conflict
		
		return new ResponseEntity<>("Book Already exists in library!", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<Object> exception(BookNotFoundException exception){
		//HttpStatus.NOT_FOUND -> The server has not found anything matching the Request-URI.
		//404 Not Found
		
		return new ResponseEntity<Object>("No such book Found! Please check for spelling and try again, or, contact Admin!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmailAlreadyExistsException.class)
	public ResponseEntity<Object> exception(EmailAlreadyExistsException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Email-Id already taken! Enter a unique Email ID!", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = NoRecordsFoundException.class)
	   public ResponseEntity<Object> exception(NoRecordsFoundException exception) {
		
		//HttpStatus.NOT_FOUND -> The server has not found anything matching the Request-URI.
		
		//404 Not Found
	      return new ResponseEntity<>("No such records found!", HttpStatus.NOT_FOUND);
	   }
	
	@ExceptionHandler(value = UserIdPasswordMismatchException.class)
	   public ResponseEntity<Object> exception(UserIdPasswordMismatchException exception) {
		//HttpStatus.UNAUTHORIZED -> The request requires user authentication. 
		//401 Unauthorized
		
	      return new ResponseEntity<>("Wrong password! Please try again!", HttpStatus.UNAUTHORIZED);
	   }
	
	@ExceptionHandler(value = UserNotFoundException.class)
	   public ResponseEntity<Object> exception(UserNotFoundException exception) {
		//HttpStatus.UNAUTHORIZED -> The request requires user authentication. 
		//401 Unauthorized
		
	      return new ResponseEntity<>("No such user found! Please enter correct credentials!", HttpStatus.UNAUTHORIZED);
	   }
	
	@ExceptionHandler(value = DuplicateKeyException.class)
	public ResponseEntity<Object> exception(DuplicateKeyException exception){
		//HttpStatus.CONFLICT -> The request could not be completed due to a conflict with the current state of the resource. 
		//This code is only allowed in situations where it is expected that 
		//the user might be able to resolve the conflict and resubmit the request. 
				
		//409 Conflict
		return new ResponseEntity<Object>("Already exists in the system!", HttpStatus.CONFLICT);
	}
}
