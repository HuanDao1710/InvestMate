package vn.edu.hust.investmate.service.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LargeShareHolderUpdater implements UpdaterService{
	private final CompanyRepository companyRepository;
	private final RestTemplate restTemplate;
	private final LargeShareHolderRepository largeShareHolderRepository;
	private final LargeShareHolderMapper mapper;

	@Override
//	@Scheduled(fixedRate = 24 * 60 * 60* 1000)
	public void update()  {
//		if(!Constant.UPDATE) return;
		List<CompanyEntity> companyEntityList = companyRepository.findAll();
		for(var companyEntity : companyEntityList) {
			updateStockData(companyEntity);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity) {
		var request = new RequestHelper<LargeShareHolderDTO, Object>(restTemplate);
		request.withUri(API.API_LARGE_SHARE_HOLDER.replace("{symbol}", companyEntity.getCode()));
		request.withHeader(APIHeader.getTCBHeader());
		try {
			var results = request.get(new ParameterizedTypeReference<>() {});

			// Lấy danh sách cổ đông từ DTO
			List<LargeShareHolderDTO.ShareHolder> shareHolders = results.getListShareHolder();
			if(shareHolders.isEmpty()) {
				return;
			}
			largeShareHolderRepository.deleteByCompanyEntity(companyEntity);

			var needSaves = shareHolders.stream()
					.map(dto -> mapper.convertToEntity(dto, companyEntity))
					.collect(Collectors.toList());

			largeShareHolderRepository.saveAll(needSaves);
		} catch (Exception e) {
			System.out.println("ERROR LARGE_SHARE_HOLDER: "  + companyEntity.getCode());
			e.printStackTrace();
		}
	}
}
