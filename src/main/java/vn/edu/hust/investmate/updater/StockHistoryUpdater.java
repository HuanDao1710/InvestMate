package vn.edu.hust.investmate.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.APIHeader;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.StockHistoryEntity;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.repository.StockHistoryRepository;
import vn.edu.hust.investmate.untils.RequestHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockHistoryUpdater implements UpdaterService{
	private final StockHistoryRepository stockHistoryRepository;
	private final CompanyRepository companyRepository;

	private final RestTemplate restTemplate;

	@Override
	@Scheduled(fixedRate = Long.MAX_VALUE, initialDelay = 10000)
	public void update() throws JsonProcessingException {
		var request = new RequestHelper<String, Object>(restTemplate);
		String type="stock";
		long from = 1701648000;// 04/12/2023
		long to = 1701734400; // 05/12/2023
		String resolution = "1"; // 1 minute
		String code = "AAA";
		request.withUri(String.format(API.API_STOCK_HISTORY, type, from, to, code ,resolution));
		request.withHeader(APIHeader.getHeaderStockHistory());
		var results = request.get(new ParameterizedTypeReference<>() {});
		System.out.println();
	}


	public  List<StockHistoryEntity> mapJsonToEntities(String jsonString, CompanyEntity companyEntity) throws
			IOException {
		List<StockHistoryEntity> stockHistoryEntities = new ArrayList<>();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(jsonString);

		JsonNode timeNode = rootNode.path("t");
		JsonNode openNode = rootNode.path("o");
		JsonNode highNode = rootNode.path("h");
		JsonNode lowNode = rootNode.path("l");
		JsonNode closeNode = rootNode.path("c");
		JsonNode volumeNode = rootNode.path("v");

		// Kiểm tra xem có đủ dữ liệu không
		if (timeNode.isArray() && openNode.isArray() && highNode.isArray() &&
				lowNode.isArray() && closeNode.isArray() && volumeNode.isArray() &&
				timeNode.size() == openNode.size() && openNode.size() == highNode.size() &&
				highNode.size() == lowNode.size() && lowNode.size() == closeNode.size() &&
				closeNode.size() == volumeNode.size()) {

			for (int i = 0; i < timeNode.size(); i++) {
				StockHistoryEntity stockHistoryEntity = new StockHistoryEntity();
				stockHistoryEntity.setCompanyEntity(companyEntity);
				stockHistoryEntity.setTime(timeNode.get(i).asLong());
				stockHistoryEntity.setOpen(openNode.get(i).asLong());
				stockHistoryEntity.setHigh(highNode.get(i).asLong());
				stockHistoryEntity.setLow(lowNode.get(i).asLong());
				stockHistoryEntity.setClose(closeNode.get(i).asLong());
				stockHistoryEntity.setVolume(volumeNode.get(i).asLong());
				stockHistoryEntities.add(stockHistoryEntity);
			}
		}

		return stockHistoryEntities;
	}


}
