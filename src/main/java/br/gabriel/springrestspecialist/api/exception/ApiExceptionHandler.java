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

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {
        return handleException(ExceptionType.BAD_REQUEST, ex, request);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundExeption ex, WebRequest request) {
		return handleException(ExceptionType.NOT_FOUND, ex, request);
	}
	
	@ExceptionHandler(ResourceInUseExeption.class)
	public ResponseEntity<Object> handleResourceInUseExeption(ResourceInUseExeption ex, WebRequest request) {
        return handleException(ExceptionType.CONFLICT, ex, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = rootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, request);
		} else if (rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, request);
		} else if (rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, request);
		}
		
		ExceptionMessage message = buildExceptionMessage(ExceptionType.MESSAGE_NOT_READABLE, "There are one or more syntax errors on the request").build();
        return handleException(ExceptionType.MESSAGE_NOT_READABLE, ex, message.getDetail(), request);
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

		return handleException(ExceptionType.MESSAGE_NOT_READABLE, ex, message, request);
	}
	
	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, WebRequest request) {
		String message = String.format(
			"Property '%s' is not a known property",
			getPropertyPath(ex)
		);

		return handleException(ExceptionType.PROPERTY_UNRECOGNIZABLE, ex, message, request);
	}
	
	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex, WebRequest request) {
		String message = String.format(
			"Property '%s' not meant to be passed",
			getPropertyPath(ex)
		);

		return handleException(ExceptionType.PROPERTY_IGNORED, ex, message, request);
	}
	
	private ResponseEntity<Object> handleException(ExceptionType exceptionType, Exception ex, WebRequest request) {
		return handleException(exceptionType, ex, ex.getMessage(), request);
	}
	
	private ResponseEntity<Object> handleException(ExceptionType exceptionType, Exception ex, String detail, WebRequest request) {
		ExceptionMessage exceptionMessage = buildExceptionMessage(exceptionType, detail).build();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), exceptionType.getStatus(), request);
	}
}