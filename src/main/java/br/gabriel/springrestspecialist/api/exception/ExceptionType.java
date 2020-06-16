package br.gabriel.springrestspecialist.api.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionType {
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "Not found"),
	CONFLICT(HttpStatus.CONFLICT, "Conflict"),
	MESSAGE_NOT_READABLE(HttpStatus.BAD_REQUEST, "Message not readable"),
	PROPERTY_UNRECOGNIZABLE(HttpStatus.BAD_REQUEST, "Property ignored"),
	PROPERTY_IGNORED(HttpStatus.BAD_REQUEST, "Property ignored"),
	PARAMETER_MISMATCH(HttpStatus.BAD_REQUEST, "Parameter mistmatch");
	
	private HttpStatus status;
	
	private String title;
	
	private String path;
	
	ExceptionType(HttpStatus status, String title) {
		this.status = status;
		this.title = title;
		this.path = String.format("/%s", title.toLowerCase().replace(" ", "-"));
	}
}
