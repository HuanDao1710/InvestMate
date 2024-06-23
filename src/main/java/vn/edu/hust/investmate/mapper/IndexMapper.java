package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.IndexDTO;
import vn.edu.hust.investmate.domain.entity.IndexEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class IndexMapper {
	public IndexEntity toEntity(IndexDTO.IndexInfoDTO dto) {
		IndexEntity entity = new IndexEntity();
		entity.setComGroupCode(dto.getComGroupCode());
		entity.setParentComGroupCode(dto.getParentComGroupCode());
//		entity.setComGroupName(dto.getComGroupName());
//		entity.setFriendlyName(dto.getFriendlyName());
//		entity.setComGroupType(dto.getComGroupType());
//		entity.setPriority(dto.getPriority());
//		entity.setCalculateRatio(dto.getCalculateRatio());
//		entity.setCalculateReturn(dto.getCalculateReturn());
//		entity.setPriorityIcbIndustry(dto.getPriorityIcbIndustry());
//		entity.setCalculateRatioIcbIndustry(dto.getCalculateRatioIcbIndustry());
//		entity.setCalculateReturnIcbIndustry(dto.getCalculateReturnIcbIndustry());
		entity.setComGroupOrder(dto.getComGroupOrder());
//		entity.setDescription(dto.getDescription());
//		entity.setStatus(dto.getStatus());
		return entity;
	}
}
