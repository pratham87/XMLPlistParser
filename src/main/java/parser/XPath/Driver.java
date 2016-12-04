package parser.XPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import parser.NSDictionary.NSDict;
import parser.NSDictionary.Parser;

public class Driver {
	static Parser parser;

	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static void main(String[] args) {
		// Using NSDictionary
		parser = new NSDict();
		String key1 = "IsKeyChainRequired", key2 = "AppId";
		try {
			String key1Value = parser.parse(readFile("./src/main/resources/xmlplist.txt"), key1);
			System.out.println("Key: " + key1 + " Value: " + key1Value);

			// Using XPath API
			parser = new XPathAPI();
			String key2Value = parser.parse(readFile("./src/main/resources/xmlplist.txt"), key2);
			System.out.println("Key: " + key2 + " Value: " + key2Value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
