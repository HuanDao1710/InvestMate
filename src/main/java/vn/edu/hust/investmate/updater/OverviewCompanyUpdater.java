package vn.edu.hust.investmate.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.hust.investmate.constant.API;
import vn.edu.hust.investmate.constant.APIHeader;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.dto.CompanyOverviewDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.mapper.CompanyOverviewMapper;
import vn.edu.hust.investmate.repository.CompanyOverviewRepository;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.untils.RequestHelper;
import java.util.List;
@Component
@RequiredArgsConstructor
public class OverviewCompanyUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final CompanyRepository companyRepository;
	private final CompanyOverviewMapper companyOverviewMapper;
	private final CompanyOverviewRepository companyOverviewRepository;
	@Override
	@Transactional
	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update() throws JsonProcessingException {
		if(!Constant.UPDATE) return;
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		companyEntityList.stream().parallel().forEach(o->updateStockData(o));
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity) {
		CompanyOverviewDTO companyOverviewDTO = new CompanyOverviewDTO();
		var request1 = new RequestHelper<CompanyOverviewDTO.CompanyOverview, Object>(restTemplate);
		var request2 = new RequestHelper<CompanyOverviewDTO.CompanyProfile, Object>(restTemplate);
		request1.withUri(API.API_OVERVIEW_COMPANY);
		request1.withVariables("symbol", companyEntity.getCode());
		request2.withUri(API.API_PROFILE_COMPANY);
		request2.withHeader(APIHeader.getHeaderCompanyProfile());
		var result1 = request1.get(new ParameterizedTypeReference<>() {});
		var result2 = request2.get(new ParameterizedTypeReference<>() {});
		companyOverviewDTO.setCompanyOverview(result1);
		companyOverviewDTO.setCompanyProfile(result2);
		var entity = companyOverviewMapper.mapDTOtoEntity(companyOverviewDTO);
		companyOverviewRepository.save(entity);
	}
}
