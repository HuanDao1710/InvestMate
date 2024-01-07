package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_day_history")
public class StockDayHistoryEntity extends  BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "time")
	private Long time;
	@Column(name = "open")
	private double open;
	@Column(name = "high")
	private double high;
	@Column(name = "low")
	private double low;
	@Column(name = "close")
	private double close;
	@Column(name = "volume")
	private double volume;
	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;
}