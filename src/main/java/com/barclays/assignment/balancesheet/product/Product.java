package com.barclays.assignment.balancesheet.product;

import com.barclays.assignment.balancesheet.currency.Currency;

/**
 * Default Implementation of @IProduct
 * @author vbalaut
 *
 */
public class Product implements IProduct {

	private ProductClassification productClassficiation;
	private Currency currency;
	private double amount;

	/**
	 * 
	 * @param productClassficiation
	 * @param currency
	 * @param amount
	 */
	public Product(ProductClassification productClassficiation, Currency currency, double amount) {
		this.productClassficiation = productClassficiation;
		this.currency = currency;
		this.amount = amount;
	}

	/**
	 * 
	 */
	@Override
	public ProductClassification getProductClassficiation() {
		return productClassficiation;
	}

	/**
	 * 
	 */
	@Override
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * 
	 */
	@Override
	public double getAmount() {
		return amount;
	}

}
