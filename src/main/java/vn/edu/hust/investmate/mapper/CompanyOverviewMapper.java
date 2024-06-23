package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.CompanyOverviewDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.CompanyOverviewEntity;

@Component
public class CompanyOverviewMapper {
	public  CompanyOverviewEntity mapDTOtoEntity(CompanyOverviewDTO dto, CompanyEntity companyEntity) {
		CompanyOverviewEntity entity = new CompanyOverviewEntity();
		// Ánh xạ từ DTO sang Entity
		entity.setCompanyEntity(companyEntity);
		entity.setCompanyName(dto.getCompanyProfile().getCompanyName());
		entity.setShortName(dto.getCompanyOverview().getShortName());
		entity.setIndustryId(dto.getCompanyOverview().getIndustryID());
		entity.setIndustryIdv2(dto.getCompanyOverview().getIndustryIDv2());
		entity.setIndustryIdLevel2(dto.getCompanyOverview().getIndustryIdLevel2());
		entity.setIndustryIdLevel4(dto.getCompanyOverview().getIndustryIdLevel4());
		entity.setIndustry(dto.getCompanyOverview().getIndustry());
		entity.setIndustryEn(dto.getCompanyOverview().getIndustryEn());
		entity.setEstablishedYear(dto.getCompanyOverview().getEstablishedYear());
		entity.setNoEmployees(dto.getCompanyOverview().getNoEmployees());
		entity.setNoShareholders(dto.getCompanyOverview().getNoShareholders());
		entity.setForeignPercent(dto.getCompanyOverview().getForeignPercent());
		entity.setWebsite(dto.getCompanyOverview().getWebsite());
		entity.setStockRating(dto.getCompanyOverview().getStockRating());
		entity.setDeltaInWeek(dto.getCompanyOverview().getDeltaInWeek());
		entity.setDeltaInMonth(dto.getCompanyOverview().getDeltaInMonth());
		entity.setDeltaInYear(dto.getCompanyOverview().getDeltaInYear());
		entity.setOutstandingShare(dto.getCompanyOverview().getOutstandingShare());
		entity.setIssueShare(dto.getCompanyOverview().getIssueShare());
		entity.setCompanyType(dto.getCompanyOverview().getCompanyType());
		entity.setCompanyProfile(dto.getCompanyProfile().getCompanyProfile());
		entity.setHistoryDev(dto.getCompanyProfile().getHistoryDev());
		entity.setCompanyPromise(dto.getCompanyProfile().getCompanyPromise());
		entity.setBusinessRisk(dto.getCompanyProfile().getBusinessRisk());
		entity.setKeyDevelopments(dto.getCompanyProfile().getKeyDevelopments());
		entity.setBusinessStrategies(dto.getCompanyProfile().getBusinessStrategies());
		return entity;
	}

	public  CompanyOverviewEntity updateWithDTO(CompanyOverviewDTO dto, CompanyOverviewEntity entity) {
		// Ánh xạ từ DTO sang Entity
		entity.setCompanyName(dto.getCompanyProfile().getCompanyName());
		entity.setShortName(dto.getCompanyOverview().getShortName());
		entity.setIndustryId(dto.getCompanyOverview().getIndustryID());
		entity.setIndustryIdv2(dto.getCompanyOverview().getIndustryIDv2());
		entity.setIndustryIdLevel2(dto.getCompanyOverview().getIndustryIdLevel2());
		entity.setIndustryIdLevel4(dto.getCompanyOverview().getIndustryIdLevel4());
		entity.setIndustry(dto.getCompanyOverview().getIndustry());
		entity.setIndustryEn(dto.getCompanyOverview().getIndustryEn());
		entity.setEstablishedYear(dto.getCompanyOverview().getEstablishedYear());
		entity.setNoEmployees(dto.getCompanyOverview().getNoEmployees());
		entity.setNoShareholders(dto.getCompanyOverview().getNoShareholders());
		entity.setForeignPercent(dto.getCompanyOverview().getForeignPercent());
		entity.setWebsite(dto.getCompanyOverview().getWebsite());
		entity.setStockRating(dto.getCompanyOverview().getStockRating());
		entity.setDeltaInWeek(dto.getCompanyOverview().getDeltaInWeek());
		entity.setDeltaInMonth(dto.getCompanyOverview().getDeltaInMonth());
		entity.setDeltaInYear(dto.getCompanyOverview().getDeltaInYear());
		entity.setOutstandingShare(dto.getCompanyOverview().getOutstandingShare());
		entity.setIssueShare(dto.getCompanyOverview().getIssueShare());
		entity.setCompanyType(dto.getCompanyOverview().getCompanyType());
		entity.setCompanyProfile(dto.getCompanyProfile().getCompanyProfile());
		entity.setHistoryDev(dto.getCompanyProfile().getHistoryDev());
		entity.setCompanyPromise(dto.getCompanyProfile().getCompanyPromise());
		entity.setBusinessRisk(dto.getCompanyProfile().getBusinessRisk());
		entity.setKeyDevelopments(dto.getCompanyProfile().getKeyDevelopments());
		entity.setBusinessStrategies(dto.getCompanyProfile().getBusinessStrategies());
		return entity;
	}
}
