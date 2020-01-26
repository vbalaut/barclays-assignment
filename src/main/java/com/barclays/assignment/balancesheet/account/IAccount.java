package com.barclays.assignment.balancesheet.account;

import java.util.List;

import com.barclays.assignment.balancesheet.product.IProduct;

public interface IAccount {

	public void setType(AccountType accountType);

	public void addProduct(IProduct product);

	public AccountType getType();

	public double totalWorth();

	public double totalWorthBySRWA();

	public List<IProduct> getProducts();

	public double totalAsset();

	public double totalLiablilities();
}
