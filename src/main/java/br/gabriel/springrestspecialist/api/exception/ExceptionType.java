package br.gabriel.springrestspecialist.api.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionType {
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
	MESSAGE_NOT_READABLE(HttpStatus.BAD_REQUEST, "Message not readable"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "Not found"),
	CONFLICT(HttpStatus.CONFLICT, "Conflict");
	
	private HttpStatus status;
	
	private String title;
	
	private String path;
	
	ExceptionType(HttpStatus status, String title) {
		this.status = status;
		this.title = title;
		this.path = String.format("/%s", title.toLowerCase().replace(" ", "-"));
	}
}
