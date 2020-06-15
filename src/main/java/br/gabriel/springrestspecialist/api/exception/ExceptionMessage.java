package br.gabriel.springrestspecialist.api.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionMessage {
	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();
	
	private String message;
}
