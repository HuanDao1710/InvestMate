package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.LargeShareHolderDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.LargeShareHolderEntity;

@Component
public class LargeShareHolderMapper {

	public LargeShareHolderEntity convertToEntity(LargeShareHolderDTO.ShareHolder dto, CompanyEntity companyEntity) {
		LargeShareHolderEntity entity = new LargeShareHolderEntity();
		entity.setNo(dto.getNo());
		entity.setTicker(dto.getTicker());
		entity.setName(dto.getName());
		entity.setOwnPercent(dto.getOwnPercent());
		entity.setCompanyEntity(companyEntity);
		return entity;
	}

}