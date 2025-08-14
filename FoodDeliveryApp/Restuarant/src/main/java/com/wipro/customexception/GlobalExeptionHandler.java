package com.wipro.customexception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExeptionHandler {
	
	@ExceptionHandler(RES_NOT_FOUND_EXCEPTION.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(RES_NOT_FOUND_EXCEPTION ex)
	{
		ErrorMessage errrorMessage=new ErrorMessage(ex.getMessage(),"RES_NOT_FOUND_EXCEPTON");
		return new ResponseEntity<>(errrorMessage,HttpStatus.NOT_FOUND);
	}

}
