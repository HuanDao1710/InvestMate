//package vn.edu.hust.investmate.domain.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "stock_filter_week")
//public class StockFilterWeekEntity extends BaseEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Column(name = "post_tax_profit_year")
//	private Double postTaxProfitYear;
//
//	@Column(name = "post_tax_profit_quarter")
//	private Double postTaxProfitQuarter;
//
//	@Column(name = "eps_ttm")
//	private Double epsTtm; //
//
//	@Column(name = "last_quarter_trading_value")
//	private Double lastQuarterTradingValue;
//
//	@Column(name = "revenue_last_quarter_growth") //
//	private Double revenueLastQuarterGrowth;
//
//	@Column(name= "revenue_growth_last_year")
//	private Double revenueGrowthLastYear;//
//
//	@Column(name="revenue_ttm")
//	private Double revenueTtm; //
//
//	@Column(name="revenueLastYear")
//	private Double revenueLastYear; //
//
//	@Column(name="last_year_post_tax_profit")
//	private Double lastYearPostTaxProfit; //
//
//	@Column(name = "last_year_cash_flow_from_financial")
//	private Double lastYearCashFlowFromFinancial;
//
//	@Column(name = "last_year_cash_flow_from_sale")
//	private Double lastYearCashFlowFromSale;
//
//	@Column(name = "last_year_free_cash_flow")
//	private Double lastYearFreeCashFlow;
//
//	@OneToOne
//	@JoinColumn(name="code", referencedColumnName = "code")
//	private CompanyEntity companyEntity;
//}
