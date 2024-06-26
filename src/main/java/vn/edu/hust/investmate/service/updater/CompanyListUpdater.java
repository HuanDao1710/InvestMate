package vn.edu.hust.investmate.service.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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

@Service
@RequiredArgsConstructor
public class CompanyListUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final CompanyRepository companyRepository;
//	@Scheduled(fixedRate = Long.MAX_VALUE)
	@Transactional
	@Override
	public void update() {
//		if(!Constant.UPDATE) return;
		var request = new RequestHelper<String, Object>(restTemplate);
		request.withUri(API.API_STOCK_LIST);
		var results = request.get(new ParameterizedTypeReference<>() {});
//		var results = ReadFileToString.readFileToString("F:\\DATN\\backend\\invest-mate\\src\\main\\resources\\data\\list_stock.txt");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<Map<String, Object>>> jsonMap = null;
		try {
			jsonMap = mapper.readValue(results, new TypeReference<>() {});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		List<Map<String, Object>> dataList = jsonMap.get("data");
		List<CompanyEntity> companyEntityList =
		dataList.stream().parallel().map(map -> {
			CompanyEntity entity = companyRepository.findOneByCode((String)map.get("code"));
			if(entity == null) {
				entity = new CompanyEntity();
				entity.setCode((String) map.get("code"));
			}
			entity.setExchange((String) map.get("san"));
			entity.setFullNameVi((String) map.get("fullname_vi"));
			entity.setBusinessType(map.get("loaidn").toString());
			return entity;
		}). toList();
//		companyRepository.deleteAllData();
		companyRepository.saveAll(companyEntityList);
	}

}
