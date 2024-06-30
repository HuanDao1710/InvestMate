package vn.edu.hust.investmate.service.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.edu.hust.investmate.repository.StockDayHistoryRepository;
import vn.edu.hust.investmate.service.updater.*;

@Component
public class ScheduledUpdater {

	private final UpdaterService balanceSheetUpdater;
	private final UpdaterService cashFlowUpdater;
	private final UpdaterService companyListUpdater;
	private final UpdaterService financialRatioUpdater;
	private final UpdaterService incomeStatementUpdater;
	private final UpdaterService indexHistoryUpdater;
	private final UpdaterService largeShareHolderUpdater;
	private final UpdaterService overviewCompanyUpdater;
	private final UpdaterService stockDayHistoryUpdater;
	private final UpdaterService stockFilterUpdater;
	private final UpdaterService stockHistoryUpdater;
	private final UpdaterService temporaryUpdater;


	@Autowired
	public ScheduledUpdater(BalanceSheetUpdater balanceSheetUpdater,
			CashFlowUpdater cashFlowUpdater,
			CompanyListUpdater companyListUpdater,
			FinancialRatioUpdater financialRatioUpdater,
			IncomeStatementUpdater incomeStatementUpdater,
			IndexHistoryUpdater indexHistoryUpdater,
			LargeShareHolderUpdater largeShareHolderUpdater,
			OverviewCompanyUpdater overviewCompanyUpdater,
			StockDayHistoryUpdater stockDayHistoryUpdater,
			StockFilterUpdater stockFilterUpdater,
			StockHistoryUpdater stockHistoryUpdater,
			TemporaryUpdater temporaryUpdater) {

		this.companyListUpdater = companyListUpdater;
		this.balanceSheetUpdater = balanceSheetUpdater;
		this.cashFlowUpdater = cashFlowUpdater;
		this.financialRatioUpdater = financialRatioUpdater;
		this.incomeStatementUpdater = incomeStatementUpdater;
		this.indexHistoryUpdater = indexHistoryUpdater;
		this.largeShareHolderUpdater = largeShareHolderUpdater;
		this.overviewCompanyUpdater = overviewCompanyUpdater;
		this.stockDayHistoryUpdater = stockDayHistoryUpdater;
		this.stockHistoryUpdater = stockHistoryUpdater;
		this.stockFilterUpdater = stockFilterUpdater;
		this.temporaryUpdater = temporaryUpdater;
	}

	@Scheduled(cron = "0 0 18 * * SUN", zone = "Asia/Ho_Chi_Minh")
	public void performWeeklyUpdate() {
		companyListUpdater.update();
		balanceSheetUpdater.update();
		cashFlowUpdater.update();
		financialRatioUpdater.update();
		incomeStatementUpdater.update();
		largeShareHolderUpdater.update();
		overviewCompanyUpdater.update();
	}

	@Scheduled(cron = "0 0 18 * * MON-FRI", zone = "Asia/Ho_Chi_Minh")
	public void performDailyUpdate() {
		stockHistoryUpdater.update();
		indexHistoryUpdater.update();
		stockDayHistoryUpdater.update();
		stockFilterUpdater.update();
		temporaryUpdater.update();
	}



}
