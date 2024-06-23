package vn.edu.hust.investmate.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexDTO {
	private int page;
	private int pageSize;
	private int totalCount;
	private List<IndexInfoDTO> items;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class IndexInfoDTO {
		private String comGroupCode;
		private String parentComGroupCode;
//		private String comGroupName;
//		private String friendlyName;
//		private int comGroupType;
//		private int priority;
//		private int calculateRatio;
//		private int calculateReturn;
//		private int priorityIcbIndustry;
//		private int calculateRatioIcbIndustry;
//		private int calculateReturnIcbIndustry;
		private int comGroupOrder;
//		private String description;
//		private int status;
			}

}
