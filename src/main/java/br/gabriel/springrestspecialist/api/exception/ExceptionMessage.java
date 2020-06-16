package br.gabriel.springrestspecialist.api.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionMessage {
	private Integer status;
	
	private String type;
	
	private String title;
	
	private String detail;
}
