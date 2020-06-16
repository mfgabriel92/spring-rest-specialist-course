package br.gabriel.springrestspecialist.api.exception;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.databind.JsonMappingException;

public class ExceptionUtils {
	public static Throwable rootCause(Throwable throwable) {
		return getRootCause(throwable);
	}
	
	public static ExceptionMessage.ExceptionMessageBuilder buildExceptionMessage(ExceptionType exceptionType, String detail) {
		return ExceptionMessage.builder()
			.status(exceptionType.getStatus().value())
			.type(exceptionType.getPath())
			.title(exceptionType.getTitle())
			.detail(detail);
	}
	
	public static ExceptionMessage.ExceptionMessageBuilder buildExceptionMessage(BindingResult result, ExceptionType exceptionType, String detail) {
		List<ExceptionMessage.Field> fields = result.getFieldErrors()
			.stream()
			.map(field -> ExceptionMessage.Field.builder()
				.name(field.getField())
				.message(field.getDefaultMessage())
				.build()
			).collect(Collectors.toList());
		
		return buildExceptionMessage(exceptionType, detail).fields(fields);
	}

	public static String getPropertyPath(JsonMappingException ex) {
		return ex.getPath().stream().map(property -> property.getFieldName()).collect(Collectors.joining("."));
	}
}
