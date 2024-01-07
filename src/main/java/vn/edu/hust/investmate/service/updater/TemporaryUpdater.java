package vn.edu.hust.investmate.service.updater;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.constant.Constant;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.StockDayHistoryEntity;
import vn.edu.hust.investmate.domain.entity.StockHistoryEntity;
import vn.edu.hust.investmate.domain.entity.TemporaryEntity;
import vn.edu.hust.investmate.repository.CompanyRepository;
import vn.edu.hust.investmate.repository.StockDayHistoryRepository;
import vn.edu.hust.investmate.repository.StockHistoryRepository;
import vn.edu.hust.investmate.repository.TemporaryRepository;
import vn.edu.hust.investmate.untils.TimeUtils;
import java.util.List;
import java.util.Objects;

// check các hàm get Day chạy đúng
@Component
@RequiredArgsConstructor
public class TemporaryUpdater implements UpdaterService{
	private final StockHistoryRepository stockHistoryRepository;
	private final CompanyRepository companyRepository;
	private final TemporaryRepository temporaryRepository;
	private final StockDayHistoryRepository stockDayHistoryRepository;
	@Scheduled(fixedRate = Long.MAX_VALUE)
	//	@Scheduled(cron = "0 18 * * MON-FRI")
	@Override
	@Transactional
	public void update() {
		if(!Constant.UPDATE) return;
		var companyList = companyRepository.findAll();
		companyList.stream().parallel().forEach(this::updateStockData);
		// update SMG
		List<Double> rsRaws = temporaryRepository.findRsRawOrderedAsc();
		companyList.stream().parallel().forEach(companyEntity -> updateSMG(rsRaws, companyEntity));
	}

	@Transactional
	public void updateSMG (List<Double> rsRaws, CompanyEntity companyEntity) {
		TemporaryEntity entity = temporaryRepository.findOneByCompanyEntity(companyEntity);
		if(entity == null) {
			return;
		}
		entity.setSmg(PercentRanking(rsRaws, entity.getRsRaw()));
		temporaryRepository.save(entity);
	}

	public int  PercentRanking(List<Double> list, Double value) {
		double size = list.size();
		for(int i = 0; i < size; i ++) {
			if(list.get(i) >= value) {
				return  (int)((i  / size) * 100);
			}
		}
		return 99;
	}

	@Transactional
	public void updateStockData(CompanyEntity companyEntity) {
		// update
		var endTime = stockHistoryRepository.findMaxTimeByCode(companyEntity.getCode());
		if(endTime == null) {
			return;
		}
		var currentDay = TimeUtils.getDayFromEpochSeconds(endTime);
		var entityListCurrentDay = stockHistoryRepository
				.findByCompanyEntityAndTimeBetweenOrderByTimeAsc(companyEntity,currentDay.getStartOfDay(), currentDay.getEndOfDay());
		if(entityListCurrentDay == null ||entityListCurrentDay.isEmpty()) return;
		int len = entityListCurrentDay.size();
		double currentClose = entityListCurrentDay.get(len - 1).getClose();
		double currentOpen = entityListCurrentDay.get(0).getOpen();
		// entity
		TemporaryEntity entity = temporaryRepository.findOneByCompanyEntity(companyEntity);
		if(entity == null) entity = new TemporaryEntity();
		entity.setCompanyEntity(companyEntity);
		entity.setUpdateTime(entityListCurrentDay.get(len - 1).getTime());
		entity.setPrice(currentClose);
		var priceChange = getChangeBeforeTime(companyEntity, endTime, currentClose, 1);
		entity.setPriceChange(priceChange);
		entity.setPricePreference(currentClose - priceChange);
		entity.setPercentChangeDay(getChangeOneDay(currentOpen, currentClose));
		entity.setPercentChangeWeek(getChangeWeek(companyEntity, endTime, currentClose));
		entity.setPercentChangeMonth(getChangeMonth(companyEntity, endTime, currentClose));
		entity.setRsRaw(getRSRaw(companyEntity, endTime, currentClose));
		entity.setTimeSeries(getTimeSeries(entityListCurrentDay));
		entity.setAvgTradingValue20Day(calculateAvgTradingValue20Day(companyEntity, endTime));
		entity.setMarketCap(calculateMarketCap(companyEntity, currentClose));
		//update
		temporaryRepository.save(entity);

	}

	private double calculateAvgTradingValue20Day(CompanyEntity company,long endTime) {
		var day = TimeUtils.getDayBeforeTime(20, endTime);
		List<StockDayHistoryEntity> results = stockDayHistoryRepository.findByCompanyEntityAndTimeBetween(company, day.getStartOfDay(), endTime);
		return results.stream()
				.mapToDouble(e -> e.getClose() * e.getVolume())
				.average()
				.orElse(Double.NaN);
	}

	private double calculateMarketCap(CompanyEntity companyEntity, double close) {
		return companyEntity.getOverviewCompanyEntity().getIssueShare() * close;
	}

	private List<Double> getTimeSeries(List<StockHistoryEntity> entityListCurrentDay) {
		return entityListCurrentDay.stream()
				.map(StockHistoryEntity::getClose)
				.toList();
	}

	private double getChangeOneDay(double open, double close) {
		return (close - open) / open;
	}

	private double getChangeWeek(CompanyEntity companyEntity, long endTime, double currentClose) {
		var day = TimeUtils.getDayOfStartOfWeekUntil(endTime);
		return calculateChange(companyEntity, day, currentClose);
	}

	private double getChangeMonth(CompanyEntity companyEntity, long endTime, double currentClose) {
		var day = TimeUtils.getDayOfStartOfMonthUntil(endTime);
		return calculateChange(companyEntity, day, currentClose);
	}

	private double getChangeBeforeTime(CompanyEntity companyEntity, long endTime, double currentClose, int dayBefore) {
		var day = TimeUtils.getDayBeforeTime(dayBefore, endTime);
		return calculateChange(companyEntity, day, currentClose);
	}

	private double getPriceRatioBeforeTime(CompanyEntity companyEntity, long endTime, double currentClose, int dayBefore) {
		var day = TimeUtils.getDayBeforeTime(dayBefore, endTime);
		return calculateRatio(companyEntity, day, currentClose);
	}

	private double getRSRaw(CompanyEntity companyEntity, long endTime, double currentClose) {
		var before20 = getPriceRatioBeforeTime(companyEntity, endTime, currentClose, 20);
		var before60 = getPriceRatioBeforeTime(companyEntity, endTime, currentClose, 60);
		var before120 = getPriceRatioBeforeTime(companyEntity, endTime, currentClose, 120);
		return 0.4 * before20 + 0.3 * before60 + 0.3 * before120;
	}

	private double calculateChange(CompanyEntity companyEntity, TimeUtils.Day day, double currentClose) {
		var beforeClose = getBeforeClose(companyEntity, day);
		if(beforeClose == 0)
			return 0;
		return (currentClose - beforeClose) / beforeClose;
	}

	private double calculateRatio(CompanyEntity companyEntity, TimeUtils.Day day, double currentClose) {
		var beforeClose = getBeforeClose(companyEntity, day);
		if(beforeClose == 0) return 1;
		return (currentClose) / beforeClose;
	}

	private double getBeforeClose(CompanyEntity companyEntity, TimeUtils.Day day) {
		var entityList = stockDayHistoryRepository
				.findByCompanyEntityAndTimeBetweenOrderByTimeAsc(companyEntity,day.getStartOfDay(), day.getEndOfDay());
		if(entityList == null || entityList.isEmpty()) {
			Long maxTime = stockDayHistoryRepository
					.findMaxTimeBeforeAndCompanyEntity(day.getStartOfDay(), companyEntity);
			if(maxTime == null) {
				return 0;
			}
			TimeUtils.Day newDay = TimeUtils.getDayFromEpochSeconds(maxTime);
			return getBeforeClose(companyEntity, newDay);
		}
		int len = entityList.size();
		return entityList.get(len - 1).getClose();
	}

}
