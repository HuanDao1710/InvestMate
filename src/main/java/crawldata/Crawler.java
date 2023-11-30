package crawldata;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Crawler {
	public static String getSchemaTable(String table, String ticker, int i) {
		String url = "";

		if (table.equals("balance_sheet")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/finance/%s/balancesheet?yearly=%d&isAll=true", ticker, i);
		} else if (table.equals("income_statement")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/finance/%s/incomestatement?yearly=%d&isAll=true", ticker, i);
		} else if (table.equals("cash_flow")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/finance/%s/cashflow?yearly=%d&isAll=true", ticker, i);
		} else if (table.equals("financial_ratio")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/finance/%s/financialratio?yearly=%d&isAll=true", ticker, i);
		} else if (table.equals("general_rating")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/rating/%s/general?fType=TICKER", ticker);
		} else if (table.equals("business_model_rating")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/rating/%s/business-model?fType=TICKER", ticker);
		} else if (table.equals("business_operation_rating")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/rating/%s/business-operation?fType=TICKER", ticker);
		} else if (table.equals("financial_health_rating")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/rating/%s/financial-health?fType=TICKER", ticker);
		} else if (table.equals("valuation_rating")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/rating/%s/valuation?fType=TICKER", ticker);
		} else if (table.equals("industry_financial_health")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/rating/%s/financial-health?fType=INDUSTRY", ticker);
		} else if (table.equals("listing_companies")) {
			url = String.format("https://apipubaws.tcbs.com.vn/tcanalysis/v1/ticker/%s/overview", ticker);
		}

		try {
			URL endpoint = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			reader.close();
			connection.disconnect();

			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JsonElement getFirstElementFromJsonString(String jsonString) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(jsonString);

		if (jsonElement.isJsonArray()) {
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			if (jsonArray.size() > 0) {
				return jsonArray.get(0);
			}
		} else {
			return jsonElement;
		}
		return null;
	}

	public static String getStringField(JsonObject jsonObject, String fieldName) {
		if (jsonObject.has(fieldName)) {
			JsonElement fieldElement = jsonObject.get(fieldName);
			if (fieldElement.isJsonPrimitive() && fieldElement.getAsJsonPrimitive().isString()) {
				return fieldElement.getAsString();
			}
		}
		return null;
	}

	public static int getIntField(JsonObject jsonObject, String fieldName) {
		if (jsonObject.has(fieldName)) {
			JsonElement fieldElement = jsonObject.get(fieldName);
			if (fieldElement.isJsonPrimitive() && fieldElement.getAsJsonPrimitive().isNumber()) {
				return fieldElement.getAsInt();
			}
		}
		return 0;
	}

	public static boolean getBooleanField(JsonObject jsonObject, String fieldName) {
		if (jsonObject.has(fieldName)) {
			JsonElement fieldElement = jsonObject.get(fieldName);
			if (fieldElement.isJsonPrimitive() && fieldElement.getAsJsonPrimitive().isBoolean()) {
				return fieldElement.getAsBoolean();
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String ticker = "ACB";
		int i = 1;
		String jsonString = getSchemaTable("listing_companies",ticker, i);
		try {
			JsonObject firstElement = (JsonObject) getFirstElementFromJsonString(jsonString);
			if (firstElement != null) {
				String field1 = getStringField(firstElement, "field1");
				int field2 = getIntField(firstElement, "field2");
				boolean field3 = getBooleanField(firstElement, "field3");

				System.out.println("Field 1: " + field1);
				System.out.println("Field 2: " + field2);
				System.out.println("Field 3: " + field3);
			} else {
				System.out.println("Không tìm thấy phần tử đầu tiên.");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi xử lý JSON: " + e.getMessage());
		}
	}
}
