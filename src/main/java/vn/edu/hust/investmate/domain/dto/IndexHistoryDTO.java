package vn.edu.hust.investmate.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexHistoryDTO {
	private long [] t;
	private double [] o;
	private double [] h;
	private double [] l;
	private double [] c;
	private long [] v;
}
