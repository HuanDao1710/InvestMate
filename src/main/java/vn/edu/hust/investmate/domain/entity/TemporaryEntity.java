package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "temporary")
public class TemporaryEntity extends BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "price")
	private double price;

	@Column(name = "rs_raw")
	private double rsRaw;

	@Column(name = "smg")
	private double smg;

	@Column(name = "price_preference")
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

	@Column(name = "market_cap") // new
	private Double marketCap;

	@Column(name = "avg_trading_value_20day") // new
	private Double avgTradingValue20Day;

	@Column(name = "volume")
	private Double volume;

	@Column(name = "daily_trading_value")
	private Double dailyTradingValue;

	@JoinColumn(name="code", referencedColumnName = "code")
	@OneToOne
	private CompanyEntity companyEntity;
}
