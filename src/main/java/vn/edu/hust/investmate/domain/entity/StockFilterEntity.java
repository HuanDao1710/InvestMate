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
	private int eps; //!!!!!!!!!!!!!!!!!!!!!!!!!
	@Column(name = "eps_growth_1_year")
	private Double epsGrowth1Year;
	@Column(name = "last_quarter_profit_growth")
	private Double lastQuarterProfitGrowth;
	@Column
	private Double roe;
//	@Column
//	private Double beta;
	@Column(name = "gross_margin")
	private Double grossMargin;
//	@Column(name = "net_margin")
//	private Double netMargin;
	@Column
	private Double doe;
	@Column
	private Double pe;
	@Column
	private Double pb;
	@Column
	private Double asset;
	@Column(name = "ev_ebitda")
	private Double evEbitda;
	@OneToOne
	@JoinColumn(name="code", referencedColumnName = "code")
	private CompanyEntity companyEntity;
}
