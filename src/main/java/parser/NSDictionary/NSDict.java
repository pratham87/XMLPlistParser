package parser.NSDictionary;

import com.dd.plist.NSDictionary;

public class NSDict implements Parser {

	@Override
	public String parse(String xmlResponse, String key) {

		try {
			NSDictionary dict = PlistParser.parsePlistString(PlistParser.replacePlistHeader(xmlResponse));
			Object respObj = dict.get("Response");

			if (respObj != null) {
				NSDictionary respDict = (NSDictionary) respObj;
				Object value = (Object) respDict.get(key);
				return value.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
