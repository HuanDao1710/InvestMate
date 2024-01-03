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
import vn.edu.hust.investmate.domain.dto.IndexDTO;
import vn.edu.hust.investmate.mapper.IndexMapper;
import vn.edu.hust.investmate.repository.IndexRepository;
import vn.edu.hust.investmate.untils.RequestHelper;

@Component
@RequiredArgsConstructor
public class IndexUpdater implements UpdaterService{
	private final RestTemplate restTemplate;
	private final IndexRepository indexRepository;
	private final IndexMapper indexMapper;
	@Transactional
	@Override
	@Scheduled(fixedRate = Long.MAX_VALUE)
	public void update() throws JsonProcessingException, InterruptedException {
		if(!Constant.UPDATE) return;
		var request = new RequestHelper<IndexDTO, Object>(restTemplate);
		request.withUri(API.API_INDICES);
		try {
			var results = request.get(new ParameterizedTypeReference<>() {}).getItems();
			var indexEntities = results.stream().parallel().map(indexMapper::toEntity).toList();
			indexRepository.deleteAllData();
			indexRepository.saveAll(indexEntities);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
