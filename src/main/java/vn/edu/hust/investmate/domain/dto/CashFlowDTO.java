package vn.edu.hust.investmate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashFlowDTO {
	private String ticker;
	private int quarter;
	private int year;
	private double investCost;
	private double fromInvest;
	private double fromFinancial;
	private double fromSale;
	private double freeCashFlow;
}
