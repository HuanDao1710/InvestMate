package vn.edu.hust.investmate.service.updater;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.domain.entity.CompanyEntity;
import vn.edu.hust.investmate.domain.entity.FinancialRatioEntity;
import vn.edu.hust.investmate.domain.entity.IncomeStatementEntity;
import vn.edu.hust.investmate.domain.entity.StockFilterEntity;
import vn.edu.hust.investmate.repository.*;

@Component
@RequiredArgsConstructor
public class StockFilterUpdater implements UpdaterService{
	private final CompanyRepository companyRepository;
	private final StockFilterRepository stockFilterRepository;
	private final IncomeStatementRepository incomeStatementRepository;
	private final BalanceSheetRepository balanceSheetRepository;
	private final FinancialRatioRepository financialRatioRepository;


	@Scheduled(fixedRate = Long.MAX_VALUE)
	@Override
	public void update() throws JsonProcessingException, InterruptedException {
//		if(!Constant.UPDATE) return;
		var companies = companyRepository.findAll();
		companies.stream().parallel().forEach(this::updateStock);
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
				.stream().findFirst().orElse(null);
		StockFilterEntity stockFilterEntity = stockFilterRepository.findOneByCompanyEntity(company);
		if(stockFilterEntity == null) stockFilterEntity = new StockFilterEntity();
		stockFilterEntity.setIndustry(company.getOverviewCompanyEntity().getIndustry());
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
		stockFilterRepository.save(stockFilterEntity);
	}
}
