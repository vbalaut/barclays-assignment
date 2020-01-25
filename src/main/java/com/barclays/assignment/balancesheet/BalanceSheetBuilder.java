package com.barclays.assignment.balancesheet;

public class BalanceSheetBuilder {

	private BalanceSheet balanceSheet;

	public BalanceSheetBuilder(BalanceSheet balanceSheet) {
		this.balanceSheet = balanceSheet;
	}

	public BalanceSheet build() {
		return balanceSheet;
	}
	
	public BalanceSheetBuilder addAccount(Account account) {
		balanceSheet.addAccount(account);
		return this;
	}

}
