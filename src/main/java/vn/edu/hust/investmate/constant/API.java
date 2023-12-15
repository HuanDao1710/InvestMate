package vn.edu.hust.investmate.constant;

public class API {
	public  static String API_STOCK_LIST = "https://wifeed.vn/api/thong-tin-co-phieu/danh-sach-ma-chung-khoan";
	public static String API_STOCK_HISTORY = "https://services.entrade.com.vn/chart-api/v2/ohlcs/%s?from=%d&to=%d&symbol=%s&resolution=%s";
	public static String API_OVERVIEW_COMPANY = "https://apipubaws.tcbs.com.vn/tcanalysis/v1/ticker/{symbol}/overview";
	public static String API_PROFILE_COMPANY = "";
}
