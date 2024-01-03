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
import vn.edu.hust.investmate.domain.dto.FinancialRatioDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.FinancialRatioEntity;
import vn.edu.hust.investmate.mapper.FinancialRationMapper;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.repository.FinancialRatioRepository;
import vn.edu.hust.investmate.untils.RequestHelper;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FinancialRatioUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final FinancialRatioRepository financialRatioRepository;
	private  final CompanyRepository companyRepository;
	private final FinancialRationMapper financialRationMapper;
	@Override
	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update() throws JsonProcessingException, InterruptedException {
		if(!Constant.UPDATE) return;
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		for(var companyEntity : companyEntityList) {
			//1 theo năm
			updateStockData(companyEntity, 1);
			//0 theo quý
			updateStockData(companyEntity, 0);
			Thread.sleep(2000);
		}
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity,int yearly) {
		var request = new RequestHelper<List<FinancialRatioDTO>, Object>(restTemplate);
		String uri = API.API_FINANCIAL_RATIO.replace("{symbol}", companyEntity.getCode())
					.replace("{x}", Integer.toString(yearly));
		request.withUri(uri);
		try {
			var results = request.get(new ParameterizedTypeReference<>() {});
			List<FinancialRatioEntity> listF = results.stream().parallel()
					.map(dto -> financialRationMapper.toEntity(dto, companyEntity, yearly)).toList();
			financialRatioRepository.saveAll(listF);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN CODE: " + companyEntity.getCode() + " yearly: " + yearly);
		}
	}
}
