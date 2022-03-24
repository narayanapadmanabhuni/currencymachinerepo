package com.currencymachine.exception;

public class InSufficientFundsException extends RuntimeException {

	public InSufficientFundsException() {
		super();
	}

	public InSufficientFundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InSufficientFundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InSufficientFundsException(String message) {
		super(message);
	}

	public InSufficientFundsException(Throwable cause) {
		super(cause);
	}

}
