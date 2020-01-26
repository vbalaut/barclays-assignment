package com.barclays.assignment.balancesheet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.barclays.assignment.balancesheet.account.AccountBuilder;
import com.barclays.assignment.balancesheet.account.AccountType;
import com.barclays.assignment.balancesheet.account.IAccount;
import com.barclays.assignment.balancesheet.currency.Currency;
import com.barclays.assignment.balancesheet.product.IProduct;
import com.barclays.assignment.balancesheet.product.Product;
import com.barclays.assignment.balancesheet.product.ProductClassification;

class BalancesheetApplicationTests {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.0000");
	BankBalanceSheetUtility bankBalanceSheetUtility = new BankBalanceSheetUtility();
	private static BalanceSheet balanceSheet = null;
	static double expectedNetWorthSRWA = 0;
	static double expectedNetWorth = 0;

	@BeforeAll
	public static void setup() {
		balanceSheet = prepareBalanceSheet();
	}

	private static BalanceSheet prepareBalanceSheet() {
		BalanceSheetBuilder balanceSheetBuilder = BalanceSheet.builder();

		// An Internal account containing 10 billion GBP of Tier 1 capital.
		Product tier1Product = new Product(ProductClassification.TIER_1_CAPITAL, Currency.GBP, 10000000d);
		balanceSheetBuilder.addAccount(
				AccountBuilder.builder().setAccountType(AccountType.INTERNAL).setProduct(tier1Product).build());
		expectedNetWorth += tier1Product.getAmount();
		expectedNetWorthSRWA += calculateSRVA(tier1Product.getAmount(), ProductClassification.TIER_1_CAPITAL);

		// b. An Internal account holding 10 Collateralised loans each one of an amount
		// between 100,000 and 200,000 GBP.
		AccountBuilder internalAccountHolding10CollateralisedLoan = AccountBuilder.builder()
				.setAccountType(AccountType.INTERNAL);
		IntStream.range(1, 11)
				.forEach(t -> internalAccountHolding10CollateralisedLoan
						.setProduct(new Product(ProductClassification.COLLATERALISED_LOAN, Currency.GBP,
								ThreadLocalRandom.current().nextDouble(100000d, 200000d))));
		IAccount account2 = internalAccountHolding10CollateralisedLoan.build();
		balanceSheetBuilder.addAccount(account2);
		double aggregatedLoanAmount = account2.getProducts().stream().mapToDouble(IProduct::getAmount).sum();
		expectedNetWorth -= aggregatedLoanAmount;
		expectedNetWorthSRWA += account2.getProducts().stream().mapToDouble(extracted()).sum();
		// expectedNetWorthSRWA += calculateSRVA(aggregatedLoanAmount,
		// ProductClassification.COLLATERALISED_LOAN);

		// c. 10 Wholesale accounts, each holding a cash product of value between 10,000
		// and
		// 100,000 GBP, also containing two bond products with values in the same range.
		AccountBuilder wholeSaleAccountWithCashAndBond = AccountBuilder.builder().setAccountType(AccountType.WHOLESALE);
		IntStream.range(1, 11)
				.forEach(t -> wholeSaleAccountWithCashAndBond.setProduct(new Product(ProductClassification.CASH,
						Currency.GBP, ThreadLocalRandom.current().nextDouble(10000d, 100000d))));
		IntStream.range(1, 3)
				.forEach(t -> wholeSaleAccountWithCashAndBond.setProduct(new Product(ProductClassification.BOND,
						Currency.GBP, ThreadLocalRandom.current().nextDouble(10000d, 100000d))));

		IAccount account3 = wholeSaleAccountWithCashAndBond.build();

		balanceSheetBuilder.addAccount(account3);
		expectedNetWorth += account3.getProducts().stream().mapToDouble(IProduct::getAmount).sum();

		expectedNetWorthSRWA += account3.getProducts().stream()
				.filter(t -> t.getProductClassficiation() == ProductClassification.CASH).mapToDouble(extracted()).sum();
		expectedNetWorthSRWA += account3.getProducts().stream()
				.filter(t -> t.getProductClassficiation() == ProductClassification.BOND).mapToDouble(extracted()).sum();

		return balanceSheetBuilder.build();
	}

	private static ToDoubleFunction<? super IProduct> extracted() {
		return value -> value.getAmount()
				- ((value.getProductClassficiation().getRiskRating() * 0.05) * value.getAmount());
	}

	private static double calculateSRVA(double amount, ProductClassification productClassification) {
		return amount - ((productClassification.getRiskRating() * 0.05) * amount);
	}

	@Test
	public void reusableMethodWhichCalculatesTheBanksNetWorthValue() {
		double netWorth = bankBalanceSheetUtility.calculateNetWorth(balanceSheet);
		assertEquals(DECIMAL_FORMAT.format(expectedNetWorth), DECIMAL_FORMAT.format(netWorth));
	}

	@Test
	public void reusableMethodWhicCalculatesTheBanksNetWorthGBPValueUsingSRWA() {
		// SRWA(value, rating) = value – ((rating*0.05)*value)
		double netWorth = bankBalanceSheetUtility.calculateNetWorthUsingSRWA(balanceSheet);
		assertEquals(DECIMAL_FORMAT.format(expectedNetWorthSRWA), DECIMAL_FORMAT.format(netWorth));
	}
}
