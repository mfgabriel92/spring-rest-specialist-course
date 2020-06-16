package br.gabriel.springrestspecialist.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ExceptionMessage {
	private Integer status;
	
	private String type;
	
	private String title;
	
	private String detail;
}
