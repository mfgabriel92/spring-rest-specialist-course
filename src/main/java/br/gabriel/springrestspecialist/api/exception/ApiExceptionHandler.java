package br.gabriel.springrestspecialist.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;

@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e) {
		ExceptionMessage exceptionMessage = ExceptionMessage
			.builder()
			.timestamp(LocalDateTime.now())
			.message(e.getMessage())
			.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<?> handleResourceNotFouncException(ResourceNotFoundExeption e) {
		ExceptionMessage exceptionMessage = ExceptionMessage
			.builder()
			.timestamp(LocalDateTime.now())
			.message(e.getMessage())
			.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
	}
}
