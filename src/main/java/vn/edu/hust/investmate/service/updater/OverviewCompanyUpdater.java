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
import vn.edu.hust.investmate.constant.APIHeader;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.dto.CompanyOverviewDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.CompanyOverviewEntity;
import vn.edu.hust.investmate.mapper.CompanyOverviewMapper;
import vn.edu.hust.investmate.repository.CompanyOverviewRepository;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.untils.RequestHelper;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OverviewCompanyUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final CompanyRepository companyRepository;
	private final CompanyOverviewMapper companyOverviewMapper;
	private final CompanyOverviewRepository companyOverviewRepository;
	@Override
//	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update() {
//		if(!Constant.UPDATE) return;
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		for(var entity : companyEntityList) {
			updateStockData(entity);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity) {
		CompanyOverviewDTO companyOverviewDTO = new CompanyOverviewDTO();
		var request1 = new RequestHelper<CompanyOverviewDTO.CompanyOverview, Object>(restTemplate);
		var request2 = new RequestHelper<CompanyOverviewDTO.CompanyProfile, Object>(restTemplate);
		request1.withUri(API.API_OVERVIEW_COMPANY.replace("{symbol}", companyEntity.getCode()));
		request2.withUri(API.API_PROFILE_COMPANY.replace("{symbol}", companyEntity.getCode()));
		request2.withHeader(APIHeader.getTCBHeader());
		try {
			var result1 = request1.get(new ParameterizedTypeReference<>() {});
			var result2 = request2.get(new ParameterizedTypeReference<>() {});
			companyOverviewDTO.setCompanyOverview(result1);
			companyOverviewDTO.setCompanyProfile(result2);

			CompanyOverviewEntity entity;

			var olds = companyOverviewRepository.findByCompanyEntity(companyEntity);
			if(!olds.isEmpty()) {
				entity = companyOverviewMapper.updateWithDTO(companyOverviewDTO, olds.get(0));

			} else {
				entity = companyOverviewMapper.mapDTOtoEntity(companyOverviewDTO, companyEntity);
			}
			companyOverviewRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN CODE: " + companyEntity.getCode());
		}
	}
}
