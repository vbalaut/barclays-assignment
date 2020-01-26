package com.barclays.assignment.balancesheet.validation;

public interface Validator<T> {

	public void validate(T type) throws ValidationException;

}
