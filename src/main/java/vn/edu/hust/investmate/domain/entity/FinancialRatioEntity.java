package vn.edu.hust.investmate.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "financial_ratio")
public class FinancialRatioEntity extends BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "yearly")
	private int yearly;
	@Column(name = "ticker")
	private String ticker;
	@Column(name = "quarter")
	private int quarter;
	@Column(name = "year")
	private int year;
	@Column(name = "price_to_earning")
	private Double priceToEarning;
	@Column(name = "price_to_book")
	private Double priceToBook;
	@Column(name = "value_before_ebitda")
	private Double valueBeforeEbitda;
	@Column(name = "dividend")
	private Double dividend;
	@Column(name = "roe")
	private Double roe;
	@Column(name = "roa")
	private Double roa;
	@Column(name = "days_receivable")
	private int daysReceivable;
	@Column(name = "days_inventory")
	private int daysInventory;
	@Column(name = "days_payable")
	private int daysPayable;
	@Column(name = "ebit_on_interest")
	private Double ebitOnInterest;
	@Column(name = "earning_per_share")
	private int earningPerShare;
	@Column(name = "book_value_per_share")
	private int bookValuePerShare;
	@Column(name = "interest_margin")
	private Double interestMargin;
	@Column(name = "non_interest_on_toi")
	private Double nonInterestOnToi;
	@Column(name = "bad_debt_percentage")
	private Double badDebtPercentage;
	@Column(name = "provision_on_bad_debt")
	private Double provisionOnBadDebt;
	@Column(name = "cost_of_financing")
	private Double costOfFinancing;
	@Column(name = "equity_on_total_asset")
	private Double equityOnTotalAsset;
	@Column(name = "equity_on_loan")
	private Double equityOnLoan;
	@Column(name = "cost_to_income")
	private Double costToIncome;
	@Column(name = "equity_on_liability")
	private Double equityOnLiability;
	@Column(name = "current_payment")
	private Double currentPayment;
	@Column(name = "quick_payment")
	private Double quickPayment;
	@Column(name = "eps_change")
	private Double epsChange;
	@Column(name = "ebitda_on_stock")
	private int ebitdaOnStock;
	@Column(name = "gross_profit_margin")
	private Double grossProfitMargin;
	@Column(name = "operating_profit_margin")
	private Double operatingProfitMargin;
	@Column(name = "post_tax_margin")
	private Double postTaxMargin;
	@Column(name = "debt_on_equity")
	private Double debtOnEquity;
	@Column(name = "debt_on_asset")
	private Double debtOnAsset;
	@Column(name = "debt_on_ebitda")
	private Double debtOnEbitda;
	@Column(name = "short_on_long_debt")
	private Double shortOnLongDebt;
	@Column(name = "asset_on_equity")
	private Double assetOnEquity;
	@Column(name = "capital_balance")
	private int capitalBalance;
	@Column(name = "cash_on_equity")
	private Double cashOnEquity;
	@Column(name = "cash_on_capitalize")
	private Double cashOnCapitalize;
	@Column(name = "cash_circulation")
	private int cashCirculation;
	@Column(name = "revenue_on_work_capital")
	private Double revenueOnWorkCapital;
	@Column(name = "capex_on_fixed_asset")
	private Double capexOnFixedAsset;
	@Column(name = "revenue_on_asset")
	private Double revenueOnAsset;
	@Column(name = "post_tax_on_pre_tax")
	private Double postTaxOnPreTax;
	@Column(name = "ebit_on_revenue")
	private Double ebitOnRevenue;
	@Column(name = "pre_tax_on_ebit")
	private Double preTaxOnEbit;
	@Column(name = "pre_provision_on_toi")
	private Double preProvisionOnToi;
	@Column(name = "post_tax_on_toi")
	private Double postTaxOnToi;
	@Column(name = "loan_on_earn_asset")
	private Double loanOnEarnAsset;
	@Column(name = "loan_on_asset")
	private Double loanOnAsset;
	@Column(name = "loan_on_deposit")
	private Double loanOnDeposit;
	@Column(name = "deposit_on_earn_asset")
	private Double depositOnEarnAsset;
	@Column(name = "bad_debt_on_asset")
	private Double badDebtOnAsset;
	@Column(name = "liquidity_on_liability")
	private Double liquidityOnLiability;
	@Column(name = "payable_on_equity")
	private Double payableOnEquity;
	@Column(name = "cancel_debt")
	private Double cancelDebt;
	@Column(name = "ebitda_on_stock_change")
	private Double ebitdaOnStockChange;
	@Column(name = "book_value_per_share_change")
	private Double bookValuePerShareChange;
	@Column(name = "credit_growth")
	private Double creditGrowth;
	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;
}



