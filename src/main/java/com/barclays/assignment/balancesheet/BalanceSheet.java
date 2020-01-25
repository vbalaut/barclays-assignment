package com.barclays.assignment.balancesheet;

import java.util.ArrayList;
import java.util.List;

public class BalanceSheet {

	private AccountValidator accountValidator = new AccountValidator();

	private List<Account> allAccounts = new ArrayList<>();
	private BalanceSheet() {

	}

	public static BalanceSheetBuilder builder() {
		return new BalanceSheetBuilder(new BalanceSheet());
	}

	public void addAccount(Account account) {
		validateAccount(account);
		allAccounts.add(account);		
	}

	private void validateAccount(Account account) {
		accountValidator.validate(account);
		
	}

}
