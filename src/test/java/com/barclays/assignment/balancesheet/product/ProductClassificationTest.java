package com.barclays.assignment.balancesheet.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductClassificationTest {

	@Test
	void testBond() {
		ProductClassification productClassification = ProductClassification.BOND;
		assertEquals("Bond", productClassification.getName());
		assertEquals(1, productClassification.getRiskRating());
		assertEquals(ProductType.ASSET, productClassification.getProductType());
	}

	@Test
	void testCash() {
		ProductClassification productClassification = ProductClassification.CASH;
		assertEquals("Cash", productClassification.getName());
		assertEquals(2, productClassification.getRiskRating());
		assertEquals(ProductType.ASSET, productClassification.getProductType());
	}

	@Test
	void testCollateralisedLoan() {
		ProductClassification productClassification = ProductClassification.COLLATERALISED_LOAN;
		assertEquals("Collateralised Loan", productClassification.getName());
		assertEquals(3, productClassification.getRiskRating());
		assertEquals(ProductType.LIBALITY, productClassification.getProductType());
	}

	@Test
	void testTier1Capital() {
		ProductClassification productClassification = ProductClassification.TIER_1_CAPITAL;
		assertEquals("Tier 1 Capital", productClassification.getName());
		assertEquals(0, productClassification.getRiskRating());
		assertEquals(ProductType.ASSET, productClassification.getProductType());
	}
}
