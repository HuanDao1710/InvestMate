package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "balance_sheet")
public class BalanceSheetEntity extends BaseEntity {
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

	@Column(name = "short_asset")
	private double shortAsset;

	@Column(name = "cash")
	private double cash;

	@Column(name = "short_invest")
	private double shortInvest;

	@Column(name = "short_receivable")
	private double shortReceivable;

	@Column(name = "inventory")
	private double inventory;

	@Column(name = "long_asset")
	private double longAsset;

	@Column(name = "fixed_asset")
	private double fixedAsset;

	@Column(name = "asset")
	private double asset;

	@Column(name = "debt")
	private double debt;

	@Column(name = "short_debt")
	private double shortDebt;

	@Column(name = "long_debt")
	private double longDebt;

	@Column(name = "equity")
	private double equity;

	@Column(name = "capital")
	private double capital;

	@Column(name = "central_bank_deposit")
	private Double centralBankDeposit;

	@Column(name = "other_bank_deposit")
	private Double otherBankDeposit;

	@Column(name = "other_bank_loan")
	private Double otherBankLoan;

	@Column(name = "stock_invest")
	private Double stockInvest;

	@Column(name = "customer_loan")
	private Double customerLoan;

	@Column(name = "bad_loan")
	private Double badLoan;

	@Column(name = "provision")
	private Double provision;

	@Column(name = "net_customer_loan")
	private Double netCustomerLoan;

	@Column(name = "other_asset")
	private Double otherAsset;

	@Column(name = "other_bank_credit")
	private Double otherBankCredit;

	@Column(name = "owe_other_bank")
	private Double oweOtherBank;

	@Column(name = "owe_central_bank")
	private Double oweCentralBank;

	@Column(name = "valuable_paper")
	private Double valuablePaper;

	@Column(name = "payable_interest")
	private Double payableInterest;

	@Column(name = "receivable_interest")
	private Double receivableInterest;

	@Column(name = "deposit")
	private Double deposit;

	@Column(name = "other_debt")
	private double otherDebt;

	@Column(name = "fund")
	private Double fund;

	@Column(name = "undistributed_income")
	private double undistributedIncome;

	@Column(name = "minor_shareholder_profit")
	private double minorShareholderProfit;

	@Column(name = "payable")
	private double payable;

	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;
}