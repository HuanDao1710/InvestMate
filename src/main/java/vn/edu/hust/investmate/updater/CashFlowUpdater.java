package vn.edu.hust.investmate.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.dto.CashFlowDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.mapper.CashFlowMapper;
import vn.edu.hust.investmate.repository.CashFlowRepository;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.untils.RequestHelper;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CashFlowUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final CashFlowMapper cashFlowMapper;
	private final CashFlowRepository cashFlowRepository;
	private final CompanyRepository companyRepository;
	@Override
	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update() throws JsonProcessingException, InterruptedException {
		if(!Constant.UPDATE) return;
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		for(var entity : companyEntityList) {
			updateStockData(entity, 0);
			updateStockData(entity, 1);
			Thread.sleep(2000);
		}
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity, int yearly) {
		var request = new RequestHelper<List<CashFlowDTO>, Objects>(restTemplate);
		String uri = API.API_FINANCIAL_REPORT
				.replace("{symbol}", companyEntity.getCode())
				.replace("{report_type}", "cashflow");
		request.withUri(uri);
		request.withParam("yearly", yearly);
		request.withParam("isAll", true);
		try {
			var results = request.get(new ParameterizedTypeReference<>() {});
			var cashFlowEntities =  results.stream().parallel()
					.map(dto -> cashFlowMapper.toEntity(dto, companyEntity, yearly)).toList();
			cashFlowRepository.saveAll(cashFlowEntities);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN CODE: " + companyEntity.getCode());
		}
	}
}
