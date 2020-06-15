package br.gabriel.springrestspecialist.domain.exception;

public class ResourceNotFoundExeption extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundExeption(String message) {
		super(message);
	}
}