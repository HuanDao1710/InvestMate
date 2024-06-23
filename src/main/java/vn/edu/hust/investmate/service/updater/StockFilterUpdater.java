package vn.edu.hust.investmate.service.updater;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.hust.investmate.domain.entity.*;
import vn.edu.hust.investmate.repository.*;
import vn.edu.hust.investmate.untils.TimeUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockFilterUpdater implements UpdaterService{
	private final CompanyRepository companyRepository;
	private final StockFilterRepository stockFilterRepository;
	private final IncomeStatementRepository incomeStatementRepository;
	private final BalanceSheetRepository balanceSheetRepository;
	private final FinancialRatioRepository financialRatioRepository;
	private  final StockDayHistoryRepository stockDayHistoryRepository;
	private final  CashFlowRepository cashFlowRepository;


//	@Scheduled(fixedRate = Long.MAX_VALUE)
	@Override
	public void update() {
//		if(!Constant.UPDATE) return;
		var companies = companyRepository.findAll();
		companies.stream().parallel().forEach(this::updateStock);
		System.out.println("DONE!");
	}

	@Transactional
	public void updateStock(CompanyEntity company) {
		Sort sort = Sort.by(
				Sort.Order.desc("year"),
				Sort.Order.desc("quarter")
		);
		var incomeStatement = incomeStatementRepository
				.findAllByYearAndQuarter(company, 0, sort)
				.stream().findFirst().orElse(new IncomeStatementEntity());
		var financialRatio = financialRatioRepository
				.findAllByYearAndQuarter(company, 0, sort)
				.stream().findFirst().orElse(new FinancialRatioEntity());
		var financialRation1Year = financialRatioRepository
				.findAllByYearAndQuarter(company, 1, sort)
				.stream().findFirst().orElse(new FinancialRatioEntity());
		var balanceSheet = balanceSheetRepository
				.findAllByYearAndQuarter(company, 0, sort)
				.stream().findFirst().orElse(new BalanceSheetEntity());
		try {
			StockFilterEntity stockFilterEntity = stockFilterRepository.findOneByCompanyEntity(company);
			if(stockFilterEntity == null) stockFilterEntity = new StockFilterEntity();
			stockFilterEntity.setCompanyEntity(company);
			if(company.getOverviewCompanyEntity() != null) {
				stockFilterEntity.setIndustry(company.getOverviewCompanyEntity().getIndustry());
			}
			stockFilterEntity.setExchange(company.getExchange());
			stockFilterEntity.setEps(financialRatio.getEarningPerShare());
			stockFilterEntity.setEpsGrowth1Year(financialRation1Year.getEpsChange());
			stockFilterEntity.setLastQuarterProfitGrowth(incomeStatement.getQuarterOperationProfitGrowth());
			stockFilterEntity.setRoe(financialRatio.getRoe());
			stockFilterEntity.setGrossMargin(financialRatio.getGrossProfitMargin());
			stockFilterEntity.setDoe(financialRatio.getDebtOnAsset());
			stockFilterEntity.setPe(financialRatio.getPriceToEarning());
			stockFilterEntity.setPb(financialRatio.getPriceToBook());
			stockFilterEntity.setEvEbitda(financialRatio.getValueBeforeEbitda());
			stockFilterEntity.setAsset(balanceSheet.getAsset());
			updateEPS_TTM(company, stockFilterEntity);
			updateRevenueTtm(company, stockFilterEntity);
			updatePostTaxProfit(company, stockFilterEntity);
			updateCashFlowLastYear(company, stockFilterEntity);
			updateLastQuarterTradingValue(company, stockFilterEntity);
			updateRevenueAndPostTaxProfitLastYear(company, stockFilterEntity);
			updateRevenueLastQuarterAndLastYearGrowth(company, stockFilterEntity);
			stockFilterRepository.save(stockFilterEntity);
			System.out.println("COMPLETE: " + company.getCode());
		} catch (Exception e) {
			System.out.println("FAIL: " + company.getCode());
			e.printStackTrace();
		}
	}

	private StockFilterEntity updatePostTaxProfit(CompanyEntity companyEntity,
			StockFilterEntity StockFilterEntity) {
		int year = TimeUtils.getCurrentYear();
		int quarter = TimeUtils.getCurrentQuarter();
		//quý trước
		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var quarterIncomeStatements = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter, year);
		if(quarterIncomeStatements.isEmpty()) {
			StockFilterEntity.setPostTaxProfitQuarter(null);
		} else {
			StockFilterEntity.setPostTaxProfitQuarter(quarterIncomeStatements
					.get(0).getPostTaxProfit());
		}
		// năm trước
		var yearIncomeStatements = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,1,5,
						TimeUtils.getCurrentYear() - 1);
		if(yearIncomeStatements.isEmpty()) {
			StockFilterEntity.setPostTaxProfitYear(null);
		} else {
			StockFilterEntity.setPostTaxProfitYear(yearIncomeStatements.get(0).getPostTaxProfit());
		}
		return StockFilterEntity;
	}

	private void updateEPS_TTM(CompanyEntity companyEntity
			,StockFilterEntity StockFilterEntity) {
		int quarter = TimeUtils.getCurrentQuarter();
		int year = TimeUtils.getCurrentYear();
		StockFilterEntity.setEpsTtm(null);

		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var financial1s = financialRatioRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
		if(financial1s.isEmpty()){
			return;
		}

		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var financial2s = financialRatioRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
		if(financial2s.isEmpty()) {
			return;
		}

		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var financial3s = financialRatioRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
		if(financial3s.isEmpty()) {
			return;
		}

		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var financial4s = financialRatioRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter,year);
		if(financial4s.isEmpty()) {
			return;
		}

		try {
			StockFilterEntity.setEpsTtm((double) (financial1s.get(0).getEarningPerShare()
					+ financial2s.get(0).getEarningPerShare()
					+ financial3s.get(0).getEarningPerShare()
					+ financial4s.get(0).getEarningPerShare()));
		} catch (Exception ignored){}
	}


	private void updateLastQuarterTradingValue (CompanyEntity companyEntity,StockFilterEntity StockFilterEntity) {
		var lastQuarterTimeEpoch = TimeUtils.getPreviousQuarterEpochTime();
		StockFilterEntity.setLastQuarterTradingValue(null);
		try {
			List<StockDayHistoryEntity> listHistory = stockDayHistoryRepository
					.findByCompanyEntityAndTimeBetweenOrderByTimeAsc(companyEntity,lastQuarterTimeEpoch.getStartTime() , lastQuarterTimeEpoch.getEndTime());
			double tradingValue = 0.0;
			for(var day : listHistory) {
				tradingValue += day.getClose() * day.getVolume();
			}
			StockFilterEntity.setLastQuarterTradingValue(tradingValue);
		} catch (Exception e) {}
	}

	private void updateRevenueLastQuarterAndLastYearGrowth(CompanyEntity companyEntity,
			StockFilterEntity StockFilterEntity) {
		int quarter = TimeUtils.getCurrentQuarter();
		int year = TimeUtils.getCurrentYear();
		//quater
		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var incomeStatements = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
		if(incomeStatements.isEmpty()) {
			StockFilterEntity.setRevenueLastQuarterGrowth(null);
		}
		StockFilterEntity.setRevenueLastQuarterGrowth(incomeStatements.get(0)
				.getQuarterRevenueGrowth());
		//year
		var incomeStatementYears = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity, 1, 5, TimeUtils.getCurrentYear() - 1);
		if(incomeStatementYears.isEmpty()) {
			StockFilterEntity.setRevenueGrowthLastYear(null);
		}
		StockFilterEntity.setRevenueGrowthLastYear(incomeStatementYears.get(0)
				.getYearRevenueGrowth());

	}

	private void updateRevenueTtm(CompanyEntity companyEntity,
			StockFilterEntity StockFilterEntity) {

		StockFilterEntity.setRevenueTtm(null);

		int quarter = TimeUtils.getCurrentQuarter();
		int year = TimeUtils.getCurrentYear();
		//quater
		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var incomeStatements1 = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
		if (incomeStatements1.isEmpty()) return;
		//
		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var incomeStatements2 = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
		if (incomeStatements2.isEmpty()) return;
		//
		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var incomeStatements3 = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
		if (incomeStatements3.isEmpty()) return;
		//
		quarter = quarter - 1;
		if(quarter < 1) {
			quarter = 4;
			year = year - 1;
		}
		var incomeStatements4 = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,0, quarter, year );
		if (incomeStatements4.isEmpty()) return;

		try {
			StockFilterEntity.setRevenueTtm(
					incomeStatements1.get(0).getRevenue()
							+ incomeStatements2.get(0).getRevenue()
							+ incomeStatements3.get(0).getRevenue()
							+ incomeStatements4.get(0).getRevenue());
		} catch (Exception ignored) {}
	}

	private void updateRevenueAndPostTaxProfitLastYear(CompanyEntity companyEntity,
			StockFilterEntity StockFilterEntity) {

		var incomeStatments = incomeStatementRepository
				.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,
						1,5, TimeUtils.getCurrentYear() - 1);
		if(!incomeStatments.isEmpty()) {
			StockFilterEntity.setRevenueLastYear(incomeStatments.get(0).getRevenue());
			StockFilterEntity.setLastYearPostTaxProfit(incomeStatments.get(0).getPostTaxProfit());
		}
	}

	private void updateCashFlowLastYear(CompanyEntity companyEntity,
			StockFilterEntity StockFilterEntity) {
		var cashFlows = cashFlowRepository.findByCompanyEntityAndYearlyAndQuarterAndYear(companyEntity,
				1, 5, TimeUtils.getCurrentYear() - 1);
		if(!cashFlows.isEmpty()){
			StockFilterEntity.setLastYearFreeCashFlow(cashFlows.get(0).getFreeCashFlow());
			StockFilterEntity.setLastYearCashFlowFromFinancial(cashFlows.get(0).getFromFinancial());
			StockFilterEntity.setLastYearCashFlowFromSale(cashFlows.get(0).getFromSale());
		}
	}


}
