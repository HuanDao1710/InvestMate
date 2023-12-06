package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
