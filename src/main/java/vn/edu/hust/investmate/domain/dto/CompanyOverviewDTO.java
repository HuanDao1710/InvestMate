package vn.edu.hust.investmate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyOverviewDTO {
	private CompanyProfile companyProfile;
	private CompanyOverview companyOverview;
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CompanyProfile{
		private String id;
		private String companyName;
		private String ticker;
		private String companyProfile;
		private String historyDev;
		private String companyPromise;
		private String businessRisk;
		private String keyDevelopments;
		private String businessStrategies;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CompanyOverview {
		private String exchange;
		private String shortName;
		private int industryID;
		private String industryIDv2;
		private String industryIdLevel2;
		private String industryIdLevel4;
		private String industry;
		private String industryEn;
		private String establishedYear;
		private int noEmployees;
		private int noShareholders;
		private double foreignPercent;
		private String website;
		private double stockRating;
		private double deltaInWeek;
		private double deltaInMonth;
		private double deltaInYear;
		private double outstandingShare;
		private double issueShare;
		private String companyType;
		private String ticker;
	}

}



