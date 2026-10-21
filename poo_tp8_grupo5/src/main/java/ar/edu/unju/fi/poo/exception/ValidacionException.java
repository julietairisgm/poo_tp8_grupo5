package ar.edu.unju.fi.poo.exception;

public class ValidacionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}