package vn.edu.hust.investmate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialRatioDTO {
		private String ticker;
		private int quarter;
		private int year;
		private Double priceToEarning;
		private Double priceToBook;
		private Double valueBeforeEbitda;
		private Double dividend;
		private Double roe;
		private Double roa;
		private int daysReceivable;
		private int daysInventory;
		private int daysPayable;
		private Double ebitOnInterest;
		private int earningPerShare;
		private int bookValuePerShare;
		private Double interestMargin;
		private Double nonInterestOnToi;
		private Double badDebtPercentage;
		private Double provisionOnBadDebt;
		private Double costOfFinancing;
		private Double equityOnTotalAsset;
		private Double equityOnLoan;
		private Double costToIncome;
		private Double equityOnLiability;
		private Double currentPayment;
		private Double quickPayment;
		private Double epsChange;
		private int ebitdaOnStock;
		private Double grossProfitMargin;
		private Double operatingProfitMargin;
		private Double postTaxMargin;
		private Double debtOnEquity;
		private Double debtOnAsset;
		private Double debtOnEbitda;
		private Double shortOnLongDebt;
		private Double assetOnEquity;
		private int capitalBalance;
		private Double cashOnEquity;
		private Double cashOnCapitalize;
		private int cashCirculation;
		private Double revenueOnWorkCapital;
		private Double capexOnFixedAsset;
		private Double revenueOnAsset;
		private Double postTaxOnPreTax;
		private Double ebitOnRevenue;
		private Double preTaxOnEbit;
		private Double preProvisionOnToi;
		private Double postTaxOnToi;
		private Double loanOnEarnAsset;
		private Double loanOnAsset;
		private Double loanOnDeposit;
		private Double depositOnEarnAsset;
		private Double badDebtOnAsset;
		private Double liquidityOnLiability;
		private Double payableOnEquity;
		private Double cancelDebt;
		private Double ebitdaOnStockChange;
		private Double bookValuePerShareChange;
		private Double creditGrowth;
}
