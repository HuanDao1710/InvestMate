package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.StockHistoryDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.StockDayHistoryEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
public class StockDayHistoryMapper {
	public List<StockDayHistoryEntity> mapDTOtoListEntity (StockHistoryDTO stockHistoryDTO, CompanyEntity companyEntity) {
		if(hasNull(stockHistoryDTO)) return null;
		List<StockDayHistoryEntity> results = null;
		try {
			int size = stockHistoryDTO.getT().length;
			results = IntStream.range(0,size).parallel().mapToObj(i->{
				var entity =  new StockDayHistoryEntity();
				entity.setOpen(stockHistoryDTO.getO()[i]);
				entity.setLow(stockHistoryDTO.getL()[i]);
				entity.setTime(stockHistoryDTO.getT()[i]);
				entity.setHigh(stockHistoryDTO.getH()[i]);
				entity.setVolume(stockHistoryDTO.getV()[i]);
				entity.setClose(stockHistoryDTO.getC()[i]);
				entity.setCompanyEntity(companyEntity);
				return entity;
			}).toList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean hasNull(StockHistoryDTO stockHistoryDTO) {
		return Objects.isNull(stockHistoryDTO)
				|| Objects.isNull(stockHistoryDTO.getC())
				|| Objects.isNull(stockHistoryDTO.getH())
				|| Objects.isNull(stockHistoryDTO.getL())
				|| Objects.isNull(stockHistoryDTO.getV())
				|| Objects.isNull(stockHistoryDTO.getO())
				|| Objects.isNull(stockHistoryDTO.getT());
	}
}
