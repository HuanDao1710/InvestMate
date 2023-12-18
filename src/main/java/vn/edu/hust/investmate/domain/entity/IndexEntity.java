package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "index")
public class IndexEntity {
	@Id
	@Column(name = "comGroupCode")
	private String comGroupCode;
	@Column(name = "parentComGroupCode")
	private String parentComGroupCode;
	@Column(name = "comGroupName")
	private String comGroupName;
	@Column(name = "friendlyName")
	private String friendlyName;
	@Column(name = "comGroupType")
	private int comGroupType;
	@Column(name = "priority")
	private int priority;
	@Column(name = "calculateRatio")
	private int calculateRatio;
	@Column(name = "calculateReturn")
	private int calculateReturn;
	@Column(name = "priorityIcbIndustry")
	private int priorityIcbIndustry;
	@Column(name = "calculateRatioIcbIndustry")
	private int calculateRatioIcbIndustry;
	@Column(name = "calculateReturnIcbIndustry")
	private int calculateReturnIcbIndustry;
	@Column(name = "comGroupOrder")
	private int comGroupOrder;
	@Column(name = "description")
	private String description;
	@Column(name = "status")
	private int status;
}
