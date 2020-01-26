package com.barclays.assignment.balancesheet;

import java.util.ArrayList;
import java.util.List;

import com.barclays.assignment.balancesheet.account.AccountValidator;
import com.barclays.assignment.balancesheet.account.IAccount;
import com.barclays.assignment.balancesheet.validation.ValidationException;

public class BalanceSheet {

	private AccountValidator accountValidator = new AccountValidator();

	private List<IAccount> allAccounts = new ArrayList<>();

	private BalanceSheet() {

	}

	public static BalanceSheetBuilder builder() {
		return new BalanceSheetBuilder(new BalanceSheet());
	}

	public List<IAccount> getAllAccounts() {
		return allAccounts;
	}

	public void addAccount(IAccount account) {
		validateAccount(account);
		allAccounts.add(account);
	}

	private void validateAccount(IAccount account) {
		try {
			accountValidator.validate(account);
		} catch (ValidationException e) {
			throw new IllegalArgumentException("Error occurred while validating account : " + account, e);
		}
	}

}
