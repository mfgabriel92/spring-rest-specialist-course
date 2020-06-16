package br.gabriel.springrestspecialist.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
	public ResponseEntity<?> handleApiException(ApiException ex, WebRequest request) {
		ExceptionMessage exceptionMessage = buildExceptionMessage(ExceptionType.BAD_REQUEST, ex.getMessage()).build();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<?> handleResourceNotFouncException(ResourceNotFoundExeption ex, WebRequest request) {
		ExceptionMessage exceptionMessage = buildExceptionMessage(ExceptionType.NOT_FOUND, ex.getMessage()).build();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(ResourceInUseExeption.class)
	public ResponseEntity<?> handleResourceInUseExeption(ResourceInUseExeption ex, WebRequest request) {
		ExceptionMessage exceptionMessage = buildExceptionMessage(ExceptionType.CONFLICT, ex.getMessage()).build();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage exceptionMessage = buildExceptionMessage(ExceptionType.MESSAGE_NOT_READABLE, "There are one or more syntax errors on the request").build();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage.ExceptionMessageBuilder exceptionBody = ExceptionMessage.builder();
        exceptionBody.status(status.value());
        
        if (body == null) {
            body = exceptionBody.title(status.getReasonPhrase()).build();
        } else if (body instanceof String) {
            body = exceptionBody.title((String) body).build();
        }

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ExceptionMessage.ExceptionMessageBuilder buildExceptionMessage(ExceptionType exceptionType, String detail) {
		return ExceptionMessage.builder()
			.status(exceptionType.getStatus().value())
			.type(exceptionType.getPath())
			.title(exceptionType.getTitle())
			.detail(detail);
	}
}
