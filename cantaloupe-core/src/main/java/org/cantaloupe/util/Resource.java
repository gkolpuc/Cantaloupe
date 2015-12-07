package org.cantaloupe.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Resource {

	public static String epReq(String path) throws IOException {
		return readFile("src/test/resources/ep-req/" + path);
	}

	public static String esReq(String path) throws IOException {
		return readFile("src/test/resources/es-req/" + path);
	}
	
	public static String esRes(String path) throws IOException {
		return readFile("src/test/resources/es-res/" + path);
	}

	public static String readFile(String path) throws IOException {
		FileReader in = new FileReader(new File(path));
		BufferedReader reader = new BufferedReader(in);
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}

		return stringBuilder.toString();
	}

	private void saveToTarget(String path, String string) {
		try {
			FileUtils.writeStringToFile(new File(path), string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
