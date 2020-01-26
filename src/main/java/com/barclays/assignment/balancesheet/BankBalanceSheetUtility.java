package com.barclays.assignment.balancesheet;

import com.barclays.assignment.balancesheet.account.IAccount;

public final class BankBalanceSheetUtility {

	private void validateBalanceSheet(BalanceSheet balanceSheet) {
		if (balanceSheet == null) {
			throw new IllegalArgumentException("BalanceSheet cannot be null");
		}
	}

	public double calculateNetWorth(BalanceSheet balanceSheet) {
		validateBalanceSheet(balanceSheet);
		return balanceSheet.getAllAccounts().stream().mapToDouble(IAccount::totalWorth).sum();
	}

	public double calculateNetWorthUsingSRWA(BalanceSheet balanceSheet) {
		validateBalanceSheet(balanceSheet);
		return balanceSheet.getAllAccounts().stream().mapToDouble(IAccount::totalWorthBySRWA).sum();
	}

}
