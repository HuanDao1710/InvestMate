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
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.dto.IncomeStatementDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.mapper.IncomeStatementMapper;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.repository.IncomeStatementRepository;
import vn.edu.hust.investmate.untils.RequestHelper;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IncomeStatementUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final IncomeStatementMapper incomeStatementMapper;
	private final IncomeStatementRepository incomeStatementRepository;
	private final CompanyRepository companyRepository;
	@Override
//	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update() {
//		if(!Constant.UPDATE) return;
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		for(var entity : companyEntityList) {
			updateStockData(entity, 0);
			updateStockData(entity, 1);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity, int yearly) {
		var request = new RequestHelper<List<IncomeStatementDTO>, Objects>(restTemplate);
		String uri = API.API_FINANCIAL_REPORT
						.replace("{symbol}", companyEntity.getCode())
						.replace("{report_type}", "incomestatement");
		request.withUri(uri);
		request.withParam("yearly", yearly);
		request.withParam("isAll", true);
		try {
			var results = request.get(new ParameterizedTypeReference<>() {});
			var incomeStatementEntities =  results.stream().parallel()
					.map(dto -> incomeStatementMapper.toEntity(dto, companyEntity, yearly)).toList();
			incomeStatementRepository.saveAll(incomeStatementEntities);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN CODE: " + companyEntity.getCode());
		}
	}
}
