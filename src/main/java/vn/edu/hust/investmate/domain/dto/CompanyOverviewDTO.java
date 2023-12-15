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
	public class CompanyProfile{

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class CompanyOverview {

	}

}



