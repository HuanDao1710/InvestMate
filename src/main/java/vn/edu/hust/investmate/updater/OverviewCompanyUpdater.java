package vn.edu.hust.investmate.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.untils.RequestHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OverviewCompanyUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final CompanyRepository companyRepository;
	@Override
	@Transactional
	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update() throws JsonProcessingException {
		var request = new RequestHelper<String, Object>(restTemplate);
		request.withUri(API.API_STOCK_LIST);
		var results = request.get(new ParameterizedTypeReference<>() {});
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<Map<String, Object>>> jsonMap = mapper.readValue(results, new TypeReference<>() {});

		List<Map<String, Object>> dataList = jsonMap.get("data");
		List<CompanyEntity> companyEntityList = new ArrayList<>();
		for(var map : dataList) {

		}
		companyRepository.deleteAllData();
		companyRepository.saveAll(companyEntityList);
	}
}
