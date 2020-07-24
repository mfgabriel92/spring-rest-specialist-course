package br.gabriel.springrestspecialist.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
	MAX_SIZE_EXCEEDED(HttpStatus.BAD_REQUEST, "Max filesize exceeded"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "Not found"),
	CONFLICT(HttpStatus.CONFLICT, "Conflict"),
	MESSAGE_NOT_READABLE(HttpStatus.BAD_REQUEST, "Message not readable"),
	PROPERTY_UNKNOWN(HttpStatus.BAD_REQUEST, "Property unknown"),
	PROPERTY_IGNORED(HttpStatus.BAD_REQUEST, "Property ignored"),
	PARAMETER_MISMATCH(HttpStatus.BAD_REQUEST, "Parameter mistmatch"),
	INVALID_PROPERTIES(HttpStatus.BAD_REQUEST, "Invalid properties"),
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access denied");
	
	private HttpStatus status;
	
	private String title;
	
	private String path;
	
	ExceptionType(HttpStatus status, String title) {
		this.status = status;
		this.title = title;
		this.path = String.format("/%s", title.toLowerCase().replace(" ", "-"));
	}
}
