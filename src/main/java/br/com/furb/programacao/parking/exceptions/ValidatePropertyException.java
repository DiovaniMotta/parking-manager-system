package br.com.furb.programacao.parking.exceptions;

public class ValidatePropertyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidatePropertyException() {
		super();
	}

	public ValidatePropertyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ValidatePropertyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ValidatePropertyException(String arg0) {
		super(arg0);
	}

	public ValidatePropertyException(Throwable arg0) {
		super(arg0);
	}
}
