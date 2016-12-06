package br.com.furb.programacao.parking.exceptions;

public class DetachedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4255369727485811426L;

	public DetachedException() {
		super();
	}

	public DetachedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DetachedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DetachedException(String message) {
		super(message);
	}

	public DetachedException(Throwable cause) {
		super(cause);
	}

	
}
