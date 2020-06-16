package br.gabriel.springrestspecialist.api.exception;

import static br.gabriel.springrestspecialist.api.exception.ExceptionUtils.buildExceptionMessage;
import static br.gabriel.springrestspecialist.api.exception.ExceptionUtils.getPropertyPath;
import static br.gabriel.springrestspecialist.api.exception.ExceptionUtils.rootCause;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {
        return throwException(ExceptionType.BAD_REQUEST, ex, request);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundExeption ex, WebRequest request) {
		return throwException(ExceptionType.NOT_FOUND, ex, request);
	}
	
	@ExceptionHandler(ResourceInUseExeption.class)
	public ResponseEntity<Object> handleResourceInUseExeption(ResourceInUseExeption ex, WebRequest request) {
        return throwException(ExceptionType.CONFLICT, ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = rootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, request);
		}
		
		ExceptionMessage message = buildExceptionMessage(ExceptionType.MESSAGE_NOT_READABLE, "There are one or more syntax errors on the request").build();
        return throwException(ExceptionType.MESSAGE_NOT_READABLE, ex, message.getDetail(), request);
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
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, WebRequest request) {
		String message = String.format(
			"Property '%s' received value '%s' of type '%s' but requires a type '%s'",
			getPropertyPath(ex),
			ex.getValue(),
			ex.getValue().getClass().getSimpleName(), 
			ex.getTargetType().getSimpleName()
		);

		return throwException(ExceptionType.MESSAGE_NOT_READABLE, ex, message, request);
	}
	
	private ResponseEntity<Object> throwException(ExceptionType exceptionType, Exception ex, WebRequest request) {
		return throwException(exceptionType, ex, ex.getMessage(), request);
	}
	
	private ResponseEntity<Object> throwException(ExceptionType exceptionType, Exception ex, String detail, WebRequest request) {
		ExceptionMessage exceptionMessage = buildExceptionMessage(exceptionType, detail).build();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), exceptionType.getStatus(), request);
	}
}
