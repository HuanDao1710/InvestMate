package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class CompanyEntity extends BaseEntity implements Serializable {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="exchange")
	private String exchange;
	@Column(name="fullname_vi")
	private String fullNameVi;
	@Column(name = "business_type")
	private String businessType;
	@OneToOne(mappedBy = "companyEntity" , fetch = FetchType.LAZY)
	private TemporaryEntity temporaryEntity;
	@OneToOne(mappedBy = "companyEntity" , fetch = FetchType.LAZY)
	private StockFilterEntity stockFilterEntity;
	@OneToOne(mappedBy = "companyEntity" , fetch = FetchType.LAZY)
	private CompanyOverviewEntity overviewCompanyEntity;
	@OneToMany(mappedBy="companyEntity", fetch = FetchType.LAZY)
	private Set<StockHistoryEntity> histories;
	@OneToMany(mappedBy = "companyEntity", fetch = FetchType.LAZY)
	private Set<LargeShareHolderEntity> largeShareHolders;
	@OneToMany(mappedBy = "companyEntity", fetch = FetchType.LAZY)
	private Set<FinancialRatioEntity> financialRatioEntities;
	@OneToMany(mappedBy = "companyEntity", fetch = FetchType.LAZY)
	private Set<IncomeStatementEntity> incomeStatementEntities;
	@OneToMany(mappedBy = "companyEntity", fetch = FetchType.LAZY)
	private Set<BalanceSheetEntity> balanceSheetEntities;
	@OneToMany(mappedBy = "companyEntity", fetch = FetchType.LAZY)
	private Set<CashFlowEntity> cashFlowEntities;
}
