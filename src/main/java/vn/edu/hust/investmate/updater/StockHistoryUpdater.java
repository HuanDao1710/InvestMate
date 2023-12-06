package vn.edu.hust.investmate.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.APIHeader;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.repository.StockHistoryRepository;
import vn.edu.hust.investmate.untils.RequestHelper;

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
	}


}
