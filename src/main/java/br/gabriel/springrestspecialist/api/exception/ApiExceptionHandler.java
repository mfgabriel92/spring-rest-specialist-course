package br.gabriel.springrestspecialist.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e) {
		return handleException(HttpStatus.BAD_REQUEST, e);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<?> handleResourceNotFouncException(ResourceNotFoundExeption e) {
		return handleException(HttpStatus.NOT_FOUND, e);
	}
	
	@ExceptionHandler(ResourceInUseExeption.class)
	public ResponseEntity<?> handleResourceInUseExeption(ResourceInUseExeption e) {
		return handleException(HttpStatus.CONFLICT, e);
	}
	
	private ResponseEntity<?> handleException(HttpStatus status, Exception e) {
		ExceptionMessage exceptionMessage = buildExceptionMessage(e);
		return ResponseEntity.status(status).body(exceptionMessage);
	}
	
	private ExceptionMessage buildExceptionMessage(Exception e) {
		return ExceptionMessage.builder()
			.timestamp(LocalDateTime.now())
			.message(e.getMessage())
			.build();
	}
}
