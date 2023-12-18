package vn.edu.hust.investmate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceSheetDTO {
	private String ticker;
	private int quarter;
	private int year;
	private double shortAsset;
	private double cash;
	private double shortInvest;
	private double shortReceivable;
	private double inventory;
	private double longAsset;
	private double fixedAsset;
	private double asset;
	private double debt;
	private double shortDebt;
	private double longDebt;
	private double equity;
	private double capital;
	private Double centralBankDeposit;
	private Double otherBankDeposit;
	private Double otherBankLoan;
	private Double stockInvest;
	private Double customerLoan;
	private Double badLoan;
	private Double provision;
	private Double netCustomerLoan;
	private Double otherAsset;
	private Double otherBankCredit;
	private Double oweOtherBank;
	private Double oweCentralBank;
	private Double valuablePaper;
	private Double payableInterest;
	private Double receivableInterest;
	private Double deposit;
	private double otherDebt;
	private Double fund;
	private double undistributedIncome;
	private double minorShareHolderProfit;
	private double payable;
}
