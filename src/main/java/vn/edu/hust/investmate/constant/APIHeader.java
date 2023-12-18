package vn.edu.hust.investmate.constant;

import java.util.HashMap;
import java.util.Map;

public class APIHeader {
	public static Map<String,String> getHeaderStockHistory() {
		Map<String, String> entrade_headers = new HashMap<>();
		entrade_headers.put("authority", "services.entrade.com.vn");
		entrade_headers.put("accept", "application/json, text/plain, */*");
		entrade_headers.put("accept-language", "en-US,en;q=0.9");
		entrade_headers.put("dnt", "1");
		entrade_headers.put("origin", "https://banggia.dnse.com.vn");
		entrade_headers.put("referer", "https://banggia.dnse.com.vn/");
		entrade_headers.put("sec-ch-ua", "\"Edge\";v=\"114\", \"Chromium\";v=\"114\", \"Not=A?Brand\";v=\"24\"");
		entrade_headers.put("sec-ch-ua-mobile", "?0");
		entrade_headers.put("sec-ch-ua-platform", "\"Windows\"");
		entrade_headers.put("sec-fetch-dest", "empty");
		entrade_headers.put("sec-fetch-mode", "cors");
		entrade_headers.put("sec-fetch-site", "cross-site");
		entrade_headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1788.0");
		return entrade_headers;
	}

	public static Map<String, String> getTCBHeader () {
		Map<String, String> tcbs_header = new HashMap<>();
		tcbs_header.put("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"");
		tcbs_header.put("DNT", "1");
		tcbs_header.put("Accept-language", "vi");
		tcbs_header.put("sec-ch-ua-mobile", "?0");
		tcbs_header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
		tcbs_header.put("Content-Type", "application/json");
		tcbs_header.put("Accept", "application/json");
		tcbs_header.put("Referer", "https://tcinvest.tcbs.com.vn/");
		tcbs_header.put("sec-ch-ua-platform", "\"Windows\"");
		return tcbs_header;
	}
}
