package vn.edu.hust.investmate.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.untils.ReadFileToString;
import vn.edu.hust.investmate.untils.RequestHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CompanyListUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final CompanyRepository companyRepository;
	@Scheduled(fixedRate = Long.MAX_VALUE)
	@Transactional
	@Override
	public void update() throws JsonProcessingException {
		if(!Constant.UPDATE) return;
		var request = new RequestHelper<String, Object>(restTemplate);
		request.withUri(API.API_STOCK_LIST);
//		var results = request.get(new ParameterizedTypeReference<>() {
//			@Override
//			public Type getType() {
//				return super.getType();
//			}
//		});
		var results = ReadFileToString.readFileToString("F:\\DATN\\backend\\invest-mate\\src\\main\\resources\\data\\list_stock.txt");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<Map<String, Object>>> jsonMap = mapper.readValue(results, new TypeReference<>() {});

		List<Map<String, Object>> dataList = jsonMap.get("data");
		List<CompanyEntity> companyEntityList = new ArrayList<>();
		for(var map : dataList) {
			CompanyEntity entity = new CompanyEntity();
			entity.setCode((String) map.get("code"));
			entity.setExchange((String) map.get("san"));
			entity.setFullNameVi((String) map.get("fullname_vi"));
			entity.setBusinessType((String) map.get("san"));
			companyEntityList.add(entity);
		}
		companyRepository.deleteAllData();
		companyRepository.saveAll(companyEntityList);
	}
}
