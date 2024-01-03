package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "temporary")
public class TemporaryEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "price")
	private double price;
	@Column(name = "rs_raw")
	private double rsRaw;
	@Column(name = "smg")
	private double smg;
	@Column(name="price_preference")
	private double pricePreference;
	@Column(name = "price_change")
	private double priceChange;
	@Column(name = "percent_change_day")
	private double percentChangeDay;
	@Column(name = "percent_change_week")
	private double percentChangeWeek;
	@Column(name = "percent_change_month")
	private double percentChangeMonth;
	@Column(name = "update_time")
	private Long updateTime;
	@ElementCollection
	private List<Double> timeSeries;
	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;
}
