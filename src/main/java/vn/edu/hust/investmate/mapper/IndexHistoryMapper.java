package vn.edu.hust.investmate.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.dto.IndexHistoryDTO;
import vn.edu.hust.investmate.domain.entity.IndexEntity;
import vn.edu.hust.investmate.domain.entity.IndexHistoryEntity;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
public class IndexHistoryMapper {
	public List<IndexHistoryEntity> mapDTOtoListEntity (IndexHistoryDTO indexHistoryDTO, IndexEntity indexEntity) {
		if(hasNull(indexHistoryDTO)) return null;
		List<IndexHistoryEntity> results = null;
		try {
			int size = indexHistoryDTO.getT().length;
				results = IntStream.range(0,size).parallel().mapToObj(i->{
				var entity =  new IndexHistoryEntity();
				entity.setOpen(indexHistoryDTO.getO()[i]);
				entity.setLow(indexHistoryDTO.getL()[i]);
				entity.setTime(indexHistoryDTO.getT()[i]);
				entity.setHigh(indexHistoryDTO.getH()[i]);
				entity.setVolume(indexHistoryDTO.getV()[i]);
				entity.setClose(indexHistoryDTO.getC()[i]);
				entity.setIndexEntity(indexEntity);
				return entity;
			}).toList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean hasNull(IndexHistoryDTO indexHistoryDTO) {
		return Objects.isNull(indexHistoryDTO)
				|| Objects.isNull(indexHistoryDTO.getC())
				|| Objects.isNull(indexHistoryDTO.getH())
				|| Objects.isNull(indexHistoryDTO.getL())
				|| Objects.isNull(indexHistoryDTO.getV())
				|| Objects.isNull(indexHistoryDTO.getO())
				|| Objects.isNull(indexHistoryDTO.getT());
	}
}
