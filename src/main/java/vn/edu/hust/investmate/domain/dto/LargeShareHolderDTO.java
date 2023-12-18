package vn.edu.hust.investmate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LargeShareHolderDTO {
	private List<ShareHolder> listShareHolder;
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ShareHolder {
		private int no;
		private String ticker;
		private String name;
		private double ownPercent;

	}
}