package com.barclays.assignment.balancesheet;

import com.barclays.assignment.balancesheet.account.IAccount;

public class BalanceSheetBuilder {

	private BalanceSheet balanceSheet;

	public BalanceSheetBuilder(BalanceSheet balanceSheet) {
		this.balanceSheet = balanceSheet;
	}

	public BalanceSheet build() {
		return balanceSheet;
	}
	
	public BalanceSheetBuilder addAccount(IAccount account) {
		balanceSheet.addAccount(account);
		return this;
	}

}
