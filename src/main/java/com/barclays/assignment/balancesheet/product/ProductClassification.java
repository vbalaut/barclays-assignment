package com.barclays.assignment.balancesheet.product;

public enum ProductClassification {

	CASH {
		@Override
		public String getName() {
			return "Cash";
		}

		@Override
		public short getRiskRating() {
			return 2;
		}

		@Override
		public ProductType getProductType() {
			return ProductType.ASSET;
		}
	},
	BOND {
		@Override
		public String getName() {
			return "Bond";
		}

		@Override
		public short getRiskRating() {
			return 1;
		}

		@Override
		public ProductType getProductType() {
			return ProductType.ASSET;
		}
	},
	COLLATERALISED_LOAN {
		@Override
		public String getName() {
			return "Collateralised Loan";
		}

		@Override
		public short getRiskRating() {
			return 3;
		}

		@Override
		public ProductType getProductType() {
			return ProductType.LIBALITY;
		}
	},
	TIER_1_CAPITAL {
		@Override
		public String getName() {
			return "Tier 1 Capital";
		}

		@Override
		public short getRiskRating() {
			return 0;
		}

		@Override
		public ProductType getProductType() {
			return ProductType.ASSET;
		}
	};

	public abstract String getName();

	public abstract short getRiskRating();

	public abstract ProductType getProductType();
}
