package com.barclays.assignment.balancesheet.validation;

/**
 * Validation Exception to catch all exception while validating
 * 
 * @author vbalaut
 *
 */
public class ValidationException extends Exception {

	/**
	 * Constructor with exception message
	 * 
	 * @param msg
	 */
	public ValidationException(String msg) {
		super(msg);
	}

}
