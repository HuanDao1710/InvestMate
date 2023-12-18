package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.FinancialRatioDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.FinancialRatioEntity;

@Component
public class FinancialRationMapper {
	public  FinancialRatioEntity toEntity(FinancialRatioDTO dto, CompanyEntity companyEntity,int yearly) {
		FinancialRatioEntity entity = new FinancialRatioEntity();
		entity.setCompanyEntity(companyEntity);
		entity.setYearly(yearly);
		entity.setTicker(dto.getTicker());
		entity.setQuarter(dto.getQuarter());
		entity.setYear(dto.getYear());
		entity.setPriceToEarning(dto.getPriceToEarning());
		entity.setPriceToBook(dto.getPriceToBook());
		entity.setValueBeforeEbitda(dto.getValueBeforeEbitda());
		entity.setDividend(dto.getDividend());
		entity.setRoe(dto.getRoe());
		entity.setRoa(dto.getRoa());
		entity.setDaysReceivable(dto.getDaysReceivable());
		entity.setDaysInventory(dto.getDaysInventory());
		entity.setDaysPayable(dto.getDaysPayable());
		entity.setEbitOnInterest(dto.getEbitOnInterest());
		entity.setEarningPerShare(dto.getEarningPerShare());
		entity.setBookValuePerShare(dto.getBookValuePerShare());
		entity.setInterestMargin(dto.getInterestMargin());
		entity.setNonInterestOnToi(dto.getNonInterestOnToi());
		entity.setBadDebtPercentage(dto.getBadDebtPercentage());
		entity.setProvisionOnBadDebt(dto.getProvisionOnBadDebt());
		entity.setCostOfFinancing(dto.getCostOfFinancing());
		entity.setEquityOnTotalAsset(dto.getEquityOnTotalAsset());
		entity.setEquityOnLoan(dto.getEquityOnLoan());
		entity.setCostToIncome(dto.getCostToIncome());
		entity.setEquityOnLiability(dto.getEquityOnLiability());
		entity.setCurrentPayment(dto.getCurrentPayment());
		entity.setQuickPayment(dto.getQuickPayment());
		entity.setEpsChange(dto.getEpsChange());
		entity.setEbitdaOnStock(dto.getEbitdaOnStock());
		entity.setGrossProfitMargin(dto.getGrossProfitMargin());
		entity.setOperatingProfitMargin(dto.getOperatingProfitMargin());
		entity.setPostTaxMargin(dto.getPostTaxMargin());
		entity.setDebtOnEquity(dto.getDebtOnEquity());
		entity.setDebtOnAsset(dto.getDebtOnAsset());
		entity.setDebtOnEbitda(dto.getDebtOnEbitda());
		entity.setShortOnLongDebt(dto.getShortOnLongDebt());
		entity.setAssetOnEquity(dto.getAssetOnEquity());
		entity.setCapitalBalance(dto.getCapitalBalance());
		entity.setCashOnEquity(dto.getCashOnEquity());
		entity.setCashOnCapitalize(dto.getCashOnCapitalize());
		entity.setCashCirculation(dto.getCashCirculation());
		entity.setRevenueOnWorkCapital(dto.getRevenueOnWorkCapital());
		entity.setCapexOnFixedAsset(dto.getCapexOnFixedAsset());
		entity.setRevenueOnAsset(dto.getRevenueOnAsset());
		entity.setPostTaxOnPreTax(dto.getPostTaxOnPreTax());
		entity.setEbitOnRevenue(dto.getEbitOnRevenue());
		entity.setPreTaxOnEbit(dto.getPreTaxOnEbit());
		entity.setPreProvisionOnToi(dto.getPreProvisionOnToi());
		entity.setPostTaxOnToi(dto.getPostTaxOnToi());
		entity.setLoanOnEarnAsset(dto.getLoanOnEarnAsset());
		entity.setLoanOnAsset(dto.getLoanOnAsset());
		entity.setLoanOnDeposit(dto.getLoanOnDeposit());
		entity.setDepositOnEarnAsset(dto.getDepositOnEarnAsset());
		entity.setBadDebtOnAsset(dto.getBadDebtOnAsset());
		entity.setLiquidityOnLiability(dto.getLiquidityOnLiability());
		entity.setPayableOnEquity(dto.getPayableOnEquity());
		entity.setCancelDebt(dto.getCancelDebt());
		entity.setEbitdaOnStockChange(dto.getEbitdaOnStockChange());
		entity.setBookValuePerShareChange(dto.getBookValuePerShareChange());
		entity.setCreditGrowth(dto.getCreditGrowth());
		return entity;
	}

}
