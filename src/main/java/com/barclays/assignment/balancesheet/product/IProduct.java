package com.barclays.assignment.balancesheet.product;

import com.barclays.assignment.balancesheet.currency.Currency;

public interface IProduct {

	public Currency getCurrency();

	public ProductClassification getProductClassficiation();

	public double getAmount();

}
