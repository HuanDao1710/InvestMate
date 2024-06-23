package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "stock_filter")
public class StockFilterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "industry") // new
	private String industry;

	@Column(name = "exchange")
	private String exchange;

	@Column
	private int eps; //eps năm gần nhất

	@Column(name = "eps_growth_1_year") // tăng trưởng eps năm gần nhất
	private Double epsGrowth1Year;//lastquarter

	@Column(name = "last_quarter_profit_growth")
	private Double lastQuarterProfitGrowth;// tăng trưởng lợi nhuận quý gần nhất

	@Column
	private Double roe; // last quarter
//	@Column
//	private Double beta;
	@Column(name = "gross_margin")
	private Double grossMargin; // lastquarter
//	@Column(name = "net_margin")
//	private Double netMargin;
	@Column
	private Double doe; // lastquarter

	@Column
	private Double pe; // lastquarter

	@Column
	private Double pb; // lastquarter

	@Column
	private Double asset; // lastquarter

	@Column(name = "ev_ebitda")
	private Double evEbitda; // lastquarter

	@Column(name = "post_tax_profit_year")
	private Double postTaxProfitYear;

	@Column(name = "post_tax_profit_quarter")
	private Double postTaxProfitQuarter;

	@Column(name = "eps_ttm")
	private Double epsTtm; //

	@Column(name = "last_quarter_trading_value")
	private Double lastQuarterTradingValue;

	@Column(name = "revenue_last_quarter_growth") //
	private Double revenueLastQuarterGrowth;

	@Column(name= "revenue_growth_last_year")
	private Double revenueGrowthLastYear;//

	@Column(name="revenue_ttm")
	private Double revenueTtm; //

	@Column(name="revenueLastYear")
	private Double revenueLastYear; //

	@Column(name="last_year_post_tax_profit")
	private Double lastYearPostTaxProfit; //

	@Column(name = "last_year_cash_flow_from_financial")
	private Double lastYearCashFlowFromFinancial;

	@Column(name = "last_year_cash_flow_from_sale")
	private Double lastYearCashFlowFromSale;

	@Column(name = "last_year_free_cash_flow")
	private Double lastYearFreeCashFlow;

	@OneToOne
	@JoinColumn(name="code", referencedColumnName = "code")
	private CompanyEntity companyEntity;
}
