package vn.edu.hust.investmate.updater;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
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
	@Scheduled(fixedRate = Long.MAX_VALUE, initialDelay = 30000)
	public void update() throws JsonProcessingException {
		if(!Constant.UPDATE) return;
		String type="stock";
		// Lấy ngày hôm nay
		LocalDate today = LocalDate.now();
		var thatday = today.minusDays(1);
		// Đặt thời gian bắt đầu là 00:00:00
		LocalTime startTime = LocalTime.of(0, 0, 0);
		// Đặt thời gian kết thúc là 17:00:00
		LocalTime endTime = LocalTime.of(17, 0, 0);
		// Kết hợp ngày và thời gian để có một LocalDateTime
		LocalDateTime startDateTime = LocalDateTime.of(thatday, startTime);
		LocalDateTime endDateTime = LocalDateTime.of(thatday, endTime);

		// Chuyển đổi LocalDateTime sang Epoch Second (thời gian dạng long)
		long from = startDateTime.toEpochSecond(ZoneOffset.UTC);
		long to = endDateTime.toEpochSecond(ZoneOffset.UTC);
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		companyEntityList.stream().parallel().forEach(o->updateStockData(o, from, to, type));
	}

	@Transactional
	private void updateStockData(CompanyEntity companyEntity, long from, long to, String type) {
		var request = new RequestHelper<StockHistoryDTO, Object>(restTemplate);
		String resolution = "1"; // 1 minute
		String code = companyEntity.getCode();
		request.withUri(String.format(API.API_STOCK_HISTORY, type, from, to, code ,resolution));
		request.withHeader(APIHeader.getHeaderStockHistory());
		var results = request.get(new ParameterizedTypeReference<StockHistoryDTO>() {});
		var historyList = stockHistoryMapper.mapDTOtoListEntity(results,companyEntity);
		if(Objects.isNull(historyList)) return;
		stockHistoryRepository.saveAll(historyList);
	}
}
