package br.gabriel.springrestspecialist.api.exception;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;

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
		List<ExceptionMessage.Field> fields = result.getFieldErrors()
			.stream()
			.map(field -> {
				String message = messageSource.getMessage(field, LocaleContextHolder.getLocale());
				
				return ExceptionMessage.Field.builder()
					.name(field.getField())
					.message(message)
					.build();
			}).collect(Collectors.toList());
		
		return buildExceptionMessage(exceptionType, detail).fields(fields);
	}

	public String getPropertyPath(JsonMappingException ex) {
		return ex.getPath().stream().map(property -> property.getFieldName()).collect(Collectors.joining("."));
	}
}
