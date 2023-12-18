package vn.edu.hust.investmate.domain.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cash_flow")
public class CashFlowEntity extends BaseEntity{
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
	@Column(name = "invest_cost")
	private double investCost;
	@Column(name = "from_invest")
	private double fromInvest;
	@Column(name = "from_financial")
	private double fromFinancial;
	@Column(name = "from_sale")
	private double fromSale;
	@Column(name = "free_cash_flow")
	private double freeCashFlow;
	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;
}
