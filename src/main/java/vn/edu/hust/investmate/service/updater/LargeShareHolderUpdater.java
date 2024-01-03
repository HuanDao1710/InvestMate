package vn.edu.hust.investmate.service.updater;

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
import vn.edu.hust.investmate.domain.dto.LargeShareHolderDTO;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.mapper.LargeShareHolderMapper;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.repository.LargeShareHolderRepository;
import vn.edu.hust.investmate.untils.RequestHelper;

import java.util.List;
@Component
@RequiredArgsConstructor
public class LargeShareHolderUpdater implements UpdaterService{
	private final CompanyRepository companyRepository;
	private final RestTemplate restTemplate;
	private final LargeShareHolderRepository largeShareHolderRepository;
	private final LargeShareHolderMapper mapper;

	@Override
	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update() throws JsonProcessingException, InterruptedException {
		if(!Constant.UPDATE) return;
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		for(var companyEntity : companyEntityList) {
			updateStockData(companyEntity);
		}
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity) {
		var request = new RequestHelper<LargeShareHolderDTO, Object>(restTemplate);
		request.withUri(API.API_LARGE_SHARE_HOLDER.replace("{symbol}", companyEntity.getCode()));
		request.withHeader(APIHeader.getTCBHeader());
		var results = request.get(new ParameterizedTypeReference<>() {});
		// Lấy danh sách cổ đông từ DTO
		List<LargeShareHolderDTO.ShareHolder> shareHolders = results.getListShareHolder();
		for(var dto : shareHolders) {
			largeShareHolderRepository.save(mapper.convertToEntity(dto, companyEntity));
		}
	}
}
