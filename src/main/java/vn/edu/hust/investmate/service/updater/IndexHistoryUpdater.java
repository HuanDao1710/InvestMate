package vn.edu.hust.investmate.service.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.APIHeader;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.dto.IndexHistoryDTO;
import vn.edu.hust.investmate.domain.entity.IndexEntity;
import vn.edu.hust.investmate.mapper.IndexHistoryMapper;
import vn.edu.hust.investmate.repository.IndexHistoryRepository;
import vn.edu.hust.investmate.repository.IndexRepository;
import vn.edu.hust.investmate.untils.RequestHelper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class IndexHistoryUpdater implements UpdaterService{
	private final IndexHistoryRepository indexHistoryRepository;
	private final IndexRepository indexRepository;
	private final IndexHistoryMapper indexHistoryMapper;
	private final RestTemplate restTemplate;

	@Override
//	@Scheduled(fixedRate = Long.MAX_VALUE)
//	@Scheduled(cron = "0 18 * * MON-FRI")
	public void update()  {
//		if(!Constant.UPDATE) return;
		String type="index";
		List<IndexEntity> indexEntities = indexRepository.findAll();
		for(var entity : indexEntities) {
			updateStockData(entity, type);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Transactional
	public void updateStockData(IndexEntity indexEntity, String type) {
		long from = getTimeFrom(indexEntity);
		long to = Instant.now().getEpochSecond();
		var request = new RequestHelper<IndexHistoryDTO, Object>(restTemplate);
		String resolution = "1"; // 1 minute
		String code = indexEntity.getComGroupCode();
		request.withUri(String.format(API.API_STOCK_HISTORY, type, from, to, code ,resolution));
		request.withHeader(APIHeader.getHeaderStockHistory());
		try {
			var results = request.get(new ParameterizedTypeReference<>() {});
			var historyList = indexHistoryMapper.mapDTOtoListEntity(results, indexEntity);
			if(Objects.isNull(historyList)) return;
			indexHistoryRepository.saveAll(historyList);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN CODE: " + indexEntity.getComGroupCode());
		}
	}

	private long getTimeFrom(IndexEntity indexEntity) {
		Long from = indexHistoryRepository.findMaxTimeByCode(indexEntity.getComGroupCode());
		if (from != null) return from + 60;
		else {
			LocalDate date = LocalDate.of(2023, 6, 1);
			return date.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")) // UTC+7
					.toInstant()
					.getEpochSecond() + 60;
		}
	}
}
