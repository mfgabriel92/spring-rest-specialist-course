package br.gabriel.springrestspecialist.api.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionType {
	BAD_REQUEST(HttpStatus.BAD_REQUEST),
	NOT_FOUND(HttpStatus.NOT_FOUND),
	CONFLICT(HttpStatus.CONFLICT);
	
	private Integer statusCode;

	private String title;
	
	private String path;
	
	ExceptionType(HttpStatus status) {
		this.title = status.getReasonPhrase();
		this.path = String.format("/%s", status.getReasonPhrase().toLowerCase().replace(" ", "-"));
		this.statusCode = status.value();
	}
	
	public static ExceptionType get(HttpStatus status) {
		return ExceptionType.valueOf(status.getReasonPhrase().toUpperCase().replace(" ", "_"));
	}
}
