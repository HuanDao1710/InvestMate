package vn.edu.hust.investmate.service.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.APIHeader;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.dto.StockHistoryDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.mapper.StockHistoryMapper;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.repository.StockHistoryRepository;
import vn.edu.hust.investmate.untils.RequestHelper;

import java.time.*;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StockHistoryUpdater implements UpdaterService{
	private final StockHistoryRepository stockHistoryRepository;
	private final CompanyRepository companyRepository;
	private final StockHistoryMapper stockHistoryMapper;
	private final RestTemplate restTemplate;

	@Override
	@Scheduled(fixedRate = Long.MAX_VALUE)
//	@Scheduled(cron = "0 18 * * MON-FRI")
	public void update() throws JsonProcessingException, InterruptedException {
		if(!Constant.UPDATE) return;
		String type="stock";
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		for(var entity : companyEntityList) {
			updateStockData(entity, type);
			Thread.sleep(500);
		}
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity, String type) {
		long from = getTimeFrom(companyEntity);
		long to = Instant.now().getEpochSecond();
		var request = new RequestHelper<StockHistoryDTO, Object>(restTemplate);
		String resolution = "1"; // 1 minute
		String code = companyEntity.getCode();
		request.withUri(String.format(API.API_STOCK_HISTORY, type, from, to, code ,resolution));
		request.withHeader(APIHeader.getHeaderStockHistory());
		try {
			var results = request.get(new ParameterizedTypeReference<>() {});
			var historyList = stockHistoryMapper.mapDTOtoListEntity(results,companyEntity);
			if(Objects.isNull(historyList)) return;
			stockHistoryRepository.saveAll(historyList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN CODE: " +  companyEntity.getCode());
		}
	}


	private long getTimeFrom(CompanyEntity companyEntity) {
		Long from = stockHistoryRepository.findMaxTimeByCode(companyEntity.getCode());
		if (from != null) return from + 60;
		else {
			LocalDate date = LocalDate.of(2023, 6, 1);
			return date.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")) // UTC+7
					.toInstant()
					.getEpochSecond() + 60;
		}
	}
}
