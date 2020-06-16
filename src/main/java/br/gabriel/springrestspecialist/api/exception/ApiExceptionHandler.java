package br.gabriel.springrestspecialist.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e, WebRequest request) {
		return handleException(HttpStatus.BAD_REQUEST, e, request);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<?> handleResourceNotFouncException(ResourceNotFoundExeption e, WebRequest request) {
		return handleException(HttpStatus.NOT_FOUND, e, request);
	}
	
	@ExceptionHandler(ResourceInUseExeption.class)
	public ResponseEntity<?> handleResourceInUseExeption(ResourceInUseExeption e, WebRequest request) {
		return handleException(HttpStatus.CONFLICT, e, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (body == null) {
			body = buildExceptionMessage(status).build();
		} else if (body instanceof String) {
			body = buildExceptionMessage(status, ex.getMessage()).build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ResponseEntity<?> handleException(HttpStatus status, Exception e, WebRequest request) {
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), status, request);
	}
	
	private ExceptionMessage.ExceptionMessageBuilder buildExceptionMessage(HttpStatus status) {
		return buildExceptionMessage(status, status.getReasonPhrase());
	}
	
	private ExceptionMessage.ExceptionMessageBuilder buildExceptionMessage(HttpStatus status, String detail) {
		ExceptionType exception = ExceptionType.get(status);
		
		return ExceptionMessage.builder()
			.status(exception.getStatusCode())
			.type(exception.getPath())
			.title(exception.getTitle())
			.detail(detail);
	}
}
