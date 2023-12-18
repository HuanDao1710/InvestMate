package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.IncomeStatementDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.FinancialRatioEntity;
import vn.edu.hust.investmate.domain.entity.IncomeStatementEntity;

@Component
public class IncomeStatementMapper {

	public IncomeStatementEntity toEntity(IncomeStatementDTO dto, CompanyEntity companyEntity, int yearly) {
		IncomeStatementEntity entity = new IncomeStatementEntity();
		entity.setCompanyEntity(companyEntity);
		entity.setYearly(yearly);
		entity.setTicker(dto.getTicker());
		entity.setQuarter(dto.getQuarter());
		entity.setYear(dto.getYear());
		entity.setRevenue(dto.getRevenue());
		entity.setYearRevenueGrowth(dto.getYearRevenueGrowth());
		entity.setQuarterRevenueGrowth(dto.getQuarterRevenueGrowth());
		entity.setCostOfGoodSold(dto.getCostOfGoodSold());
		entity.setGrossProfit(dto.getGrossProfit());
		entity.setOperationExpense(dto.getOperationExpense());
		entity.setOperationProfit(dto.getOperationProfit());
		entity.setYearOperationProfitGrowth(dto.getYearOperationProfitGrowth());
		entity.setQuarterOperationProfitGrowth(dto.getQuarterOperationProfitGrowth());
		entity.setInterestExpense(dto.getInterestExpense());
		entity.setPreTaxProfit(dto.getPreTaxProfit());
		entity.setPostTaxProfit(dto.getPostTaxProfit());
		entity.setShareHolderIncome(dto.getShareHolderIncome());
		entity.setYearShareHolderIncomeGrowth(dto.getYearShareHolderIncomeGrowth());
		entity.setQuarterShareHolderIncomeGrowth(dto.getQuarterShareHolderIncomeGrowth());
		entity.setInvestProfit(dto.getInvestProfit());
		entity.setServiceProfit(dto.getServiceProfit());
		entity.setOtherProfit(dto.getOtherProfit());
		entity.setProvisionExpense(dto.getProvisionExpense());
		entity.setOperationIncome(dto.getOperationIncome());
		entity.setEbitda(dto.getEbitda());
		return entity;
	}
}
