package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "large_share_holder")
public class LargeShareHolderEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "no")
	private int no;
	@Column(name = "ticker")
	private String ticker;
	@Column(name = "name")
	private String name;
	@Column(name = "own_percent")
	private double ownPercent;
	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;
}
