package br.com.furb.programacao.parking.exceptions;

public class EmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7464736120870770476L;

	public EmptyException() {
		super();
	}

	public EmptyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyException(String message) {
		super(message);
	}

	public EmptyException(Throwable cause) {
		super(cause);
	}

	
}
