package com.barclays.assignment.balancesheet.account;

import com.barclays.assignment.balancesheet.product.IProduct;

public final class AccountBuilder {

	IAccount account = new Account();

	public static AccountBuilder builder() {

		return new AccountBuilder();
	}

	public AccountBuilder setAccountType(AccountType accountType) {
		account.setType(accountType);
		return this;
	}

	public AccountBuilder setProduct(IProduct product) {
		account.addProduct(product);
		return this;
	}

	public IAccount build() {
		return account;
	}

}
