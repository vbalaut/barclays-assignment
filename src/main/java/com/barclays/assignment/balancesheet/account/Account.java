package com.barclays.assignment.balancesheet.account;

import java.util.ArrayList;
import java.util.List;

import com.barclays.assignment.balancesheet.product.IProduct;
import com.barclays.assignment.balancesheet.product.ProductType;

public final class Account implements IAccount {

	private AccountType accountType;
	private List<IProduct> products = new ArrayList<>();

	public void addProduct(IProduct product) {
		products.add(product);
	}

	public void setType(AccountType accountType) {
		this.accountType = accountType;

	}

	@Override
	public AccountType getType() {
		return accountType;
	}

	@Override
	public double totalAsset() {
		return aggregateAmount(ProductType.ASSET);
	}

	@Override
	public double totalLiablilities() {
		return aggregateAmount(ProductType.LIBALITY);
	}

	@Override
	public double totalWorth() {
		return totalAsset() - totalLiablilities();
	}

	@Override
	public List<IProduct> getProducts() {
		return products;
	}

	private double aggregateAmount(ProductType productType) {
		return products.stream().filter(t -> t.getProductClassficiation().getProductType() == productType)
				.mapToDouble(IProduct::getAmount).sum();
	}

	@Override
	public double totalWorthBySRWA() {
		return products.stream().mapToDouble(value -> value.getAmount()
				- ((value.getProductClassficiation().getRiskRating() * 0.05) * value.getAmount())).sum();

	}

}
