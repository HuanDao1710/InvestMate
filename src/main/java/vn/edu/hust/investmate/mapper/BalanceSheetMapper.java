package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.BalanceSheetDTO;
import vn.edu.hust.investmate.domain.entity.BalanceSheetEntity;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;

@Component
public class BalanceSheetMapper {
	public  BalanceSheetEntity toEntity(BalanceSheetDTO dto, CompanyEntity companyEntity, int yearly) {
		BalanceSheetEntity entity = new BalanceSheetEntity();
		entity.setCompanyEntity(companyEntity);
		entity.setYearly(yearly);
		entity.setTicker(dto.getTicker());
		entity.setQuarter(dto.getQuarter());
		entity.setYear(dto.getYear());
		entity.setShortAsset(dto.getShortAsset());
		entity.setCash(dto.getCash());
		entity.setShortInvest(dto.getShortInvest());
		entity.setInventory(dto.getInventory());
		entity.setLongAsset(dto.getLongAsset());
		entity.setFixedAsset(dto.getFixedAsset());
		entity.setAsset(dto.getAsset());
		entity.setDebt(dto.getDebt());
		entity.setShortDebt(dto.getShortDebt());
		entity.setLongDebt(dto.getLongDebt());
		entity.setEquity(dto.getEquity());
		entity.setCapital(dto.getCapital());
		entity.setCentralBankDeposit(dto.getCentralBankDeposit());
		entity.setOtherBankDeposit(dto.getOtherBankDeposit());
		entity.setOtherBankLoan(dto.getOtherBankLoan());
		entity.setStockInvest(dto.getStockInvest());
		entity.setCustomerLoan(dto.getCustomerLoan());
		entity.setBadLoan(dto.getBadLoan());
		entity.setProvision(dto.getProvision());
		entity.setNetCustomerLoan(dto.getNetCustomerLoan());
		entity.setOtherAsset(dto.getOtherAsset());
		entity.setOtherBankCredit(dto.getOtherBankCredit());
		entity.setOweOtherBank(dto.getOweOtherBank());
		entity.setOweCentralBank(dto.getOweCentralBank());
		entity.setValuablePaper(dto.getValuablePaper());
		entity.setPayableInterest(dto.getPayableInterest());
		entity.setReceivableInterest(dto.getReceivableInterest());
		entity.setDeposit(dto.getDeposit());
		entity.setOtherDebt(dto.getOtherDebt());
		entity.setFund(dto.getFund());
		entity.setUndistributedIncome(dto.getUndistributedIncome());
		entity.setMinorShareholderProfit(dto.getMinorShareHolderProfit());
		entity.setPayable(dto.getPayable());
		return entity;
	}
}
