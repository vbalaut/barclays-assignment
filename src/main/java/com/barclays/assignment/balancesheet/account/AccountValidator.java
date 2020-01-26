package com.barclays.assignment.balancesheet.account;

import com.barclays.assignment.balancesheet.product.IProduct;
import com.barclays.assignment.balancesheet.product.ProductClassification;
import com.barclays.assignment.balancesheet.validation.ValidationException;
import com.barclays.assignment.balancesheet.validation.Validator;

public final class AccountValidator implements Validator<IAccount> {

	@Override
	public void validate(IAccount account) throws ValidationException {
		if (account.getType() == AccountType.WHOLESALE) {
			if (account.getProducts().stream().mapToDouble(IProduct::getAmount).sum() < 35000d) {
				throw new ValidationException(
						"Wholesale accounts can only be opened with a minimum balance of 35,000 GBP (across all products).");
			}

			if (account.getType() != AccountType.INTERNAL) {
				if (account.getProducts().stream()
						.filter(t -> t.getProductClassficiation() == ProductClassification.TIER_1_CAPITAL)
						.count() > 0) {
					throw new ValidationException(
							"The Tier 1 Capital product can only be held by an Internal account.");
				}
			}

		}
	}
}
