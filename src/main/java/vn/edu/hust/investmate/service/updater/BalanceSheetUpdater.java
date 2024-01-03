package vn.edu.hust.investmate.service.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.dto.BalanceSheetDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.mapper.BalanceSheetMapper;
import vn.edu.hust.investmate.repository.BalanceSheetRepository;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.untils.RequestHelper;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class BalanceSheetUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final BalanceSheetMapper balanceSheetMapper;
	private final BalanceSheetRepository balanceSheetRepository;
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
		var request = new RequestHelper<List<BalanceSheetDTO>, Objects>(restTemplate);
		String uri = API.API_FINANCIAL_REPORT
				.replace("{symbol}", companyEntity.getCode())
				.replace("{report_type}", "balancesheet");
		request.withUri(uri);
		request.withParam("yearly", yearly);
		request.withParam("isAll", true);
		try {
			var results = request.get(new ParameterizedTypeReference<>() {});
			var balanceSheetEntities =  results.stream().parallel()
					.map(dto -> balanceSheetMapper.toEntity(dto, companyEntity, yearly)).toList();
			balanceSheetRepository.saveAll(balanceSheetEntities);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN CODE: " + companyEntity.getCode());
		}
	}
}
