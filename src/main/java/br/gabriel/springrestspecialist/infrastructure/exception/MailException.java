package br.gabriel.springrestspecialist.infrastructure.exception;

public class MailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }
}
