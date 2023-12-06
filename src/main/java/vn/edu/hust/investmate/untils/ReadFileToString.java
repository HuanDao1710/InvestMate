package vn.edu.hust.investmate.untils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileToString {

	public static String readFileToString(String filePath) {
		StringBuilder content = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
			String line;
			while ((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content.toString();
	}
}