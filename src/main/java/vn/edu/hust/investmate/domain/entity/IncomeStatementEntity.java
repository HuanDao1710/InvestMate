package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "income_statement")
public class IncomeStatementEntity extends  BaseEntity implements Serializable {
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
	@Column(name = "revenue")
	private double revenue;
	@Column(name = "year_revenue_growth")
	private double yearRevenueGrowth;
	@Column(name = "quarter_revenue_growth")
	private Double quarterRevenueGrowth;
	@Column(name = "cost_of_good_sold")
	private double costOfGoodSold;
	@Column(name = "gross_profit")
	private double grossProfit;
	@Column(name = "operation_expense")
	private double operationExpense;
	@Column(name = "operation_profit")
	private double operationProfit;
	@Column(name = "year_operation_profit_growth")
	private double yearOperationProfitGrowth;
	@Column(name = "quarter_operation_profit_growth")
	private Double quarterOperationProfitGrowth;
	@Column(name = "interest_expense")
	private double interestExpense;
	@Column(name = "pre_tax_profit")
	private double preTaxProfit;
	@Column(name = "post_tax_profit")
	private double postTaxProfit;
	@Column(name = "share_holder_income")
	private double shareHolderIncome;
	@Column(name = "year_share_holder_income_growth")
	private double yearShareHolderIncomeGrowth;
	@Column(name = "quarter_share_holder_income_growth")
	private Double quarterShareHolderIncomeGrowth;
	@Column(name = "invest_profit")
	private Double investProfit;
	@Column(name = "service_profit")
	private Double serviceProfit;
	@Column(name = "other_profit")
	private Double otherProfit;
	@Column(name = "provision_expense")
	private Double provisionExpense;
	@Column(name = "operation_income")
	private Double operationIncome;
	@Column(name = "ebitda")
	private double ebitda;
	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;

}
