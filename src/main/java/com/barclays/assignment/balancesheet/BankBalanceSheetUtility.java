package com.barclays.assignment.balancesheet;

public final class BankBalanceSheetUtility {

	public double calculateNetWorthUsingSRWA(BalanceSheet balanceSheet) {
		validateBalanceSheet(balanceSheet);
		return 0;
	}

	private void validateBalanceSheet(BalanceSheet balanceSheet) {
		if(balanceSheet == null) {
			throw new IllegalArgumentException("BalanceSheet cannot be null");
		}
	}

	public double calculateNetWorth(BalanceSheet balanceSheet) {
		validateBalanceSheet(balanceSheet);
		return 0;
	}

}
