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
@Table(name = "stock_history")
public class StockHistoryEntity extends  BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "time")
	private Long time;
	@Column(name = "open")
	private Long open;
	@Column(name = "high")
	private Long high;
	@Column(name = "low")
	private Long low;
	@Column(name = "close")
	private Long close;
	@Column(name = "volume")
	private Long volume;
	@ManyToOne
	@JoinColumn(name="code", nullable=false)
	private CompanyEntity companyEntity;

}
