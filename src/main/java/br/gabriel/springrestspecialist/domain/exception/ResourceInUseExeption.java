package br.gabriel.springrestspecialist.domain.exception;

public class ResourceInUseExeption extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceInUseExeption(String message) {
		super(message);
	}
}