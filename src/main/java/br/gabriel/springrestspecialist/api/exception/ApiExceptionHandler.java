package br.gabriel.springrestspecialist.api.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private ExceptionUtils utils;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnhandledExceptions(Exception ex, WebRequest request) {
        return handleException(ExceptionType.INTERNAL_SERVER_ERROR, ex, "An internal error happened. Try again or contact us.", request);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {
        return handleException(ExceptionType.BAD_REQUEST, ex, request);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundExeption ex, WebRequest request) {
		return handleException(ExceptionType.NOT_FOUND, ex, request);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
	    Throwable rootCause = utils.rootCause(ex);
	    String detail = ex.getMostSpecificCause().toString();
	    
	    if (rootCause instanceof SQLIntegrityConstraintViolationException) {
	        return handleSQLIntegrityConstraintViolationException((SQLIntegrityConstraintViolationException) rootCause, request);
	    }
	    
        return handleException(ExceptionType.CONFLICT, ex, detail, request);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String detail = String.format(
            "The parameter of value '%s' is invalid because it requires a type '%s'",
            ex.getValue(),
            ex.getRequiredType().getSimpleName()
        );
        return handleException(ExceptionType.PARAMETER_MISMATCH, ex, detail, request);
    }
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = String.format("The resource '%s' was not found", ex.getRequestURL());
		return handleException(ExceptionType.NOT_FOUND, ex, detail, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = String.format("One or more fields are invalid. Correct them and try again.");
		ExceptionMessage exceptionMessage = utils.buildExceptionMessage(ExceptionType.INVALID_PROPERTIES, detail, ex.getBindingResult()).build();
		return handleException(ExceptionType.INVALID_PROPERTIES, ex, exceptionMessage.getDetail(), exceptionMessage, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = utils.rootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, request);
		} else if (rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, request);
		} else if (rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, request);
		}
		
        return handleException(ExceptionType.MESSAGE_NOT_READABLE, ex, "There are one or more syntax errors on the request", request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage.ExceptionMessageBuilder exceptionBody = ExceptionMessage.builder();
        exceptionBody.status(status.value());
        
        if (body == null) {
            body = exceptionBody
            	.title(status.getReasonPhrase())
            	.status(status.value())
            	.detail("An internal error happened. Try again or contact us.")
            	.build();
        }

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        return handleException(ExceptionType.CONFLICT, ex, "The resource is being used by another and cannot be deleted", request);
    }
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, WebRequest request) {
		String detail = String.format(
			"Property '%s' received value '%s' of type '%s' but requires a type '%s'",
			utils.getPropertyPath(ex),
			ex.getValue(),
			ex.getValue().getClass().getSimpleName(), 
			ex.getTargetType().getSimpleName()
		);

		return handleException(ExceptionType.MESSAGE_NOT_READABLE, ex, detail, request);
	}
	
	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, WebRequest request) {
		String detail = String.format("Property '%s' is not a known property", utils.getPropertyPath(ex));
		return handleException(ExceptionType.PROPERTY_UNKOWN, ex, detail, request);
	}
	
	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex, WebRequest request) {
		String detail = String.format("Property '%s' not meant to be passed", utils.getPropertyPath(ex));
		return handleException(ExceptionType.PROPERTY_IGNORED, ex, detail, request);
	}
	
	private ResponseEntity<Object> handleException(ExceptionType exceptionType, Exception ex, WebRequest request) {
		return handleException(exceptionType, ex, ex.getMessage(), request);
	}
	
	private ResponseEntity<Object> handleException(ExceptionType exceptionType, Exception ex, String detail, WebRequest request) {
		ExceptionMessage exceptionMessage = utils.buildExceptionMessage(exceptionType, detail).build();
        return handleException(exceptionType, ex, exceptionMessage.getDetail(), exceptionMessage, request);
	}
	
	private ResponseEntity<Object> handleException(ExceptionType exceptionType, Exception ex, String detail, ExceptionMessage exceptionMessage, WebRequest request) {
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), exceptionType.getStatus(), request);
	}
}
