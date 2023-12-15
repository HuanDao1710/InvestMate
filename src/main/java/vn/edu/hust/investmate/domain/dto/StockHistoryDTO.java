package vn.edu.hust.investmate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockHistoryDTO {
	private long [] t;
	private long [] o;
	private long [] h;
	private long [] l;
	private long [] c;
	private long [] v;
}
