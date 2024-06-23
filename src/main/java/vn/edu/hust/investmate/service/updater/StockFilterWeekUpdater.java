//package vn.edu.hust.investmate.service.updater;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import vn.edu.hust.investmate.domain.entity.CompanyEntity;
//import vn.edu.hust.investmate.domain.entity.StockDayHistoryEntity;
//import vn.edu.hust.investmate.domain.entity.StockFilterWeekEntity;
//import vn.edu.hust.investmate.repository.*;
//import vn.edu.hust.investmate.untils.TimeUtils;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Component
//public class StockFilterWeekUpdater implements UpdaterService{
//	private final CompanyRepository companyRepository;
//	private final BalanceSheetRepository balanceSheetRepository;
//	private  final IncomeStatementRepository incomeStatementRepository;
//	private final StockFilterWeekRepository stockFilterWeekRepository;
//	private final FinancialRatioRepository financialRatioRepository;
//	private final StockDayHistoryRepository stockDayHistoryRepository;
//	private final CashFlowRepository cashFlowRepository;
//
//	@Transactional
//	@Override
//	public void update() throws JsonProcessingException, InterruptedException {
//    var listCompany = companyRepository.findAll();
//
//	}
//
//	private StockFilterWeekEntity updatePostTaxProfit(CompanyEntity companyEntity,
//			StockFilterWeekEntity stockFilterWeekEntity) {
//		int year = TimeUtils.getCurrentYear();
//		int quarter = TimeUtils.getCurrentQuarter();
//		//quý trước
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var quarterIncomeStatements = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter, year);
//		if(quarterIncomeStatements.isEmpty()) {
//			stockFilterWeekEntity.setPostTaxProfitQuarter(null);
//		} else {
//			stockFilterWeekEntity.setPostTaxProfitQuarter(quarterIncomeStatements
//					.get(0).getPostTaxProfit());
//		}
//		// năm trước
//		var yearIncomeStatements = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,1,5,
//						TimeUtils.getCurrentYear() - 1);
//		if(yearIncomeStatements.isEmpty()) {
//			stockFilterWeekEntity.setPostTaxProfitYear(null);
//		} else {
//			stockFilterWeekEntity.setPostTaxProfitYear(yearIncomeStatements.get(0).getPostTaxProfit());
//		}
//		return stockFilterWeekEntity;
//	}
//
//	private StockFilterWeekEntity updateEPS_TTM(CompanyEntity companyEntity
//			,StockFilterWeekEntity stockFilterWeekEntity) {
//		int quarter = TimeUtils.getCurrentQuarter();
//		int year = TimeUtils.getCurrentYear();
//		stockFilterWeekEntity.setEpsTtm(null);
//
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var financial1s = financialRatioRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
//		if(financial1s.isEmpty()){
//			return stockFilterWeekEntity;
//		}
//
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var financial2s = financialRatioRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
//		if(financial2s.isEmpty()) {
//			return stockFilterWeekEntity;
//		}
//
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var financial3s = financialRatioRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
//		if(financial3s.isEmpty()) {
//			return stockFilterWeekEntity;
//		}
//
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var financial4s = financialRatioRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
//		if(financial4s.isEmpty()) {
//			return stockFilterWeekEntity;
//		}
//
//		try {
//			stockFilterWeekEntity.setEpsTtm((double) (financial1s.get(0).getEarningPerShare()
//					+ financial2s.get(0).getEarningPerShare()
//					+ financial3s.get(0).getEarningPerShare()
//					+ financial4s.get(0).getEarningPerShare()));
//		} catch (Exception ignored){}
//		return stockFilterWeekEntity;
//	}
//
//
//	private StockFilterWeekEntity updateLastQuarterTraidingValue (CompanyEntity companyEntity,StockFilterWeekEntity stockFilterWeekEntity) {
//		var lastQuarterTimeEpoch = TimeUtils.getPreviousQuarterEpochTime();
//		stockFilterWeekEntity.setLastQuarterTradingValue(null);
//		try {
//			List<StockDayHistoryEntity> listHistory = stockDayHistoryRepository
//					.findByCompanyEntityAndTimeBetweenOrderByTimeAsc(companyEntity,lastQuarterTimeEpoch.getStartTime() , lastQuarterTimeEpoch.getEndTime());
//			Double tradingValue = 0.0;
//			for(var day : listHistory) {
//				tradingValue += day.getClose() * day.getVolume();
//			}
//			stockFilterWeekEntity.setLastQuarterTradingValue(tradingValue);
//		} catch (Exception e) {}
//		return stockFilterWeekEntity;
//	}
//
//	private StockFilterWeekEntity updateRevenueLastQuarterAndLastYearGrowth(CompanyEntity companyEntity,
//			StockFilterWeekEntity stockFilterWeekEntity) {
//		int quarter = TimeUtils.getCurrentQuarter();
//		int year = TimeUtils.getCurrentYear();
//		//quater
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var incomeStatements = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
//		if(incomeStatements.isEmpty()) {
//			stockFilterWeekEntity.setRevenueLastQuarterGrowth(null);
//		}
//		stockFilterWeekEntity.setRevenueLastQuarterGrowth(incomeStatements.get(0)
//				.getQuarterRevenueGrowth());
//		//year
//		var incomeStatementYears = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity, 1, 5, TimeUtils.getCurrentYear() - 1);
//		if(incomeStatementYears.isEmpty()) {
//			stockFilterWeekEntity.setRevenueGrowthLastYear(null);
//		}
//		stockFilterWeekEntity.setRevenueGrowthLastYear(incomeStatementYears.get(0)
//				.getYearRevenueGrowth());
//
//		return stockFilterWeekEntity;
//	}
//
//	private StockFilterWeekEntity updateRevenueTtm(CompanyEntity companyEntity,
//			StockFilterWeekEntity stockFilterWeekEntity) {
//
//		stockFilterWeekEntity.setRevenueTtm(null);
//
//		int quarter = TimeUtils.getCurrentQuarter();
//		int year = TimeUtils.getCurrentYear();
//		//quater
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var incomeStatements1 = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
//		if (incomeStatements1.isEmpty()) return stockFilterWeekEntity;
//		//
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var incomeStatements2 = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
//		if (incomeStatements2.isEmpty()) return stockFilterWeekEntity;
//		//
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var incomeStatements3 = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
//		if (incomeStatements3.isEmpty()) return stockFilterWeekEntity;
//		//
//		quarter = quarter - 1;
//		if(quarter < 0) {
//			quarter = 4;
//			year = year - 1;
//		}
//		var incomeStatements4 = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
//		if (incomeStatements4.isEmpty()) return stockFilterWeekEntity;
//
//		try {
//			stockFilterWeekEntity.setRevenueTtm(
//					incomeStatements1.get(0).getRevenue()
//					+ incomeStatements2.get(0).getRevenue()
//					+ incomeStatements3.get(0).getRevenue()
//					+ incomeStatements4.get(0).getRevenue());
//		} catch (Exception ignored) {}
//		return stockFilterWeekEntity;
//	}
//
//	private StockFilterWeekEntity updateRevenueAndPostTaxProfitLastYear(CompanyEntity companyEntity,
//			StockFilterWeekEntity stockFilterWeekEntity) {
//
//		var incomeStatments = incomeStatementRepository
//				.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,
//						1,5, TimeUtils.getCurrentYear() - 1);
//		if(!incomeStatments.isEmpty()) {
//			stockFilterWeekEntity.setRevenueLastYear(incomeStatments.get(0).getRevenue());
//			stockFilterWeekEntity.setLastYearPostTaxProfit(incomeStatments.get(0).getPostTaxProfit());
//		}
//		return stockFilterWeekEntity;
//	}
//
//	private StockFilterWeekEntity updateCashFlowLastYear(CompanyEntity companyEntity,
//			StockFilterWeekEntity stockFilterWeekEntity) {
//		var cashFlows = cashFlowRepository.findByCompanyEntityYearlyAndQuarterAndYear(companyEntity,
//				1, 5, TimeUtils.getCurrentYear() - 1);
//		if(!cashFlows.isEmpty()){
//			stockFilterWeekEntity.setLastYearFreeCashFlow(cashFlows.get(0).getFreeCashFlow());
//			stockFilterWeekEntity.setLastYearCashFlowFromFinancial(cashFlows.get(0).getFromFinancial());
//			stockFilterWeekEntity.setLastYearCashFlowFromSale(cashFlows.get(0).getFromSale());
//		}
//		return stockFilterWeekEntity;
//	}
//
//
//
//
//
//
//}
