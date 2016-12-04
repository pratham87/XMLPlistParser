package parser.NSDictionary;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;

public class PlistParser {
	public static String replacePlistHeader(String plistStr) {
		String rplStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		plistStr = plistStr.substring(plistStr.indexOf("<plist"), plistStr.length());
		String nPlist = rplStr + plistStr;
		return nPlist;
	}

	/**
	 * This method parses the plist string and creates an NSDictionary object
	 * 
	 * @param plistStr
	 *            String containing the plist to be parsed
	 * @return NSDictionary object containing the values parsed form the plist
	 * @throws Exception
	 */
	public static NSDictionary parsePlistString(String plistStr) throws Exception {

		return (NSDictionary) PropertyListParser.parse(plistStr.getBytes("UTF-8"));

	}
}
