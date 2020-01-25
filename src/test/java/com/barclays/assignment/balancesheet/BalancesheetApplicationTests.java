package com.barclays.assignment.balancesheet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BalancesheetApplicationTests {

	BankBalanceSheetUtility bankBalanceSheetUtility = new BankBalanceSheetUtility();
	private static BalanceSheet balanceSheet = null;
	double expectedNetWorthSRWA = 0;
	double expectedNetWorth = 0;

	@BeforeAll
	public static void setup() {

		balanceSheet = prepareBalanceSheet();

	}

	private static BalanceSheet prepareBalanceSheet() {
		// TODO Auto-generated method stub
		BalanceSheetBuilder balanceSheetBuilder = BalanceSheet.builder();
		return balanceSheetBuilder.build();
	}

	@Test
	public void reusableMethodWhichCalculatesTheBanksNetWorthValue() {
		double netWorth = bankBalanceSheetUtility.calculateNetWorth(balanceSheet);
		assertEquals(expectedNetWorth, netWorth);
	}

	@Test
	public void reusableMethodWhicCalculatesTheBanksNetWorthGBPValueUsingSRWA() {
		// SRWA(value, rating) = value – ((rating*0.05)*value)
		double netWorth = bankBalanceSheetUtility.calculateNetWorthUsingSRWA(balanceSheet);
		assertEquals(expectedNetWorthSRWA, netWorth);
	}

//	2. Demonstrate a reusable method which calculates the banks Net Worth value.
//	(the sum of assets value minus the sum of liabilities value) in GBP.
//	3. Demonstrate a reusable method which calculates the banks Net Worth GBP value using the
//	sum of Simple Risk Weighted Assets (SRWA) values as an input. Use the formula:

}
