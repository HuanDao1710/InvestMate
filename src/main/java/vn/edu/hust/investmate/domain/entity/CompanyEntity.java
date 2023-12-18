package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class CompanyEntity extends BaseEntity{
	@Id
	@Column(name="code")
	private String code;
	@Column(name="exchange")
	private String exchange;
	@Column(name="fullname_vi")
	private String fullNameVi;
	@Column(name = "business_type")
	private String businessType;
	@OneToOne(mappedBy = "companyEntity")
	private CompanyOverviewEntity overviewCompanyEntity;
	@OneToMany(mappedBy="companyEntity")
	private Set<StockHistoryEntity> histories;
	@OneToMany(mappedBy = "companyEntity")
	private Set<LargeShareHolderEntity> largeShareHolders;
	@OneToMany(mappedBy = "companyEntity")
	private Set<FinancialRatioEntity> financialRatioEntities;
	@OneToMany(mappedBy = "companyEntity")
	private Set<IncomeStatementEntity> incomeStatementEntities;
	@OneToMany(mappedBy = "companyEntity")
	private Set<BalanceSheetEntity> balanceSheetEntities;
	@OneToMany(mappedBy = "companyEntity")
	private Set<CashFlowEntity> cashFlowEntities;

}
