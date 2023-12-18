package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.CashFlowDTO;
import vn.edu.hust.investmate.domain.entity.CashFlowEntity;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;

@Component
public class CashFlowMapper {
	public CashFlowEntity toEntity(CashFlowDTO dto, CompanyEntity companyEntity, int yearly) {
		CashFlowEntity entity = new CashFlowEntity();
		entity.setCompanyEntity(companyEntity);
		entity.setYearly(yearly);
		entity.setTicker(dto.getTicker());
		entity.setQuarter(dto.getQuarter());
		entity.setYear(dto.getYear());
		entity.setInvestCost(dto.getInvestCost());
		entity.setFromInvest(dto.getFromInvest());
		entity.setFromFinancial(dto.getFromFinancial());
		entity.setFromSale(dto.getFromSale());
		entity.setFreeCashFlow(dto.getFreeCashFlow());
		return entity;
	}
}
