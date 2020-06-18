package br.gabriel.springrestspecialist.api.exception;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.databind.JsonMappingException;

public class ExceptionUtils {
	@Autowired
	private MessageSource messageSource;
	
	public Throwable rootCause(Throwable throwable) {
		return getRootCause(throwable);
	}
	
	public ExceptionMessage.ExceptionMessageBuilder buildExceptionMessage(ExceptionType exceptionType, String detail) {
		return ExceptionMessage.builder()
			.status(exceptionType.getStatus().value())
			.type(exceptionType.getPath())
			.title(exceptionType.getTitle())
			.detail(detail);
	}
	
	public ExceptionMessage.ExceptionMessageBuilder buildExceptionMessage(ExceptionType exceptionType, String detail, BindingResult result) {
		List<ExceptionMessage.Object> objs = result.getAllErrors()
			.stream()
			.map(error -> {
				String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
				String name = error.getObjectName();
				
				if (error instanceof FieldError) {
				    name = ((FieldError) error).getField();
				}
				
				return ExceptionMessage.Object.builder()
					.name(name)
					.message(message)
					.build();
			}).collect(Collectors.toList());
		
		return buildExceptionMessage(exceptionType, detail).objects(objs);
	}

	public String getPropertyPath(JsonMappingException ex) {
		return ex.getPath().stream().map(property -> property.getFieldName()).collect(Collectors.joining("."));
	}
}
