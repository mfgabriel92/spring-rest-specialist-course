package br.gabriel.springrestspecialist.api.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
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
	
	private List<Object> objects;
	
	@ApiModel("Object")
	@Getter
	@Builder
	public static class Object {
		private String name;
		
		private String message;
	}
}
