package com.artist.vote.demo.global.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.artist.vote.demo.bean.ErrorDetails;
import com.artist.vote.demo.global.application.exceptions.ArtistNotFoundException;
import com.artist.vote.demo.util.ArtistUtil;

@ControllerAdvice
public class ArtistCustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails details = new ErrorDetails(ArtistUtil.getTimestamp(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ArtistNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ErrorDetails details = new ErrorDetails(ArtistUtil.getTimestamp(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails details = new ErrorDetails(ArtistUtil.getTimestamp(), ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		return new ResponseEntity(details, HttpStatus.BAD_REQUEST);
	}

}
