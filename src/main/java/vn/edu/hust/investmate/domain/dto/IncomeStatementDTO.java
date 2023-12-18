package vn.edu.hust.investmate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeStatementDTO {
	private String ticker;
	private int quarter;
	private int year;
	private double revenue;
	private double yearRevenueGrowth;
	private Double quarterRevenueGrowth;
	private double costOfGoodSold;
	private double grossProfit;
	private double operationExpense;
	private double operationProfit;
	private double yearOperationProfitGrowth;
	private Double quarterOperationProfitGrowth;
	private double interestExpense;
	private double preTaxProfit;
	private double postTaxProfit;
	private double shareHolderIncome;
	private double yearShareHolderIncomeGrowth;
	private Double quarterShareHolderIncomeGrowth;
	private Double investProfit;
	private Double serviceProfit;
	private Double otherProfit;
	private Double provisionExpense;
	private Double operationIncome;
	private double ebitda;
}
