package parser.XPath;

import java.io.StringReader;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import parser.NSDictionary.Parser;

public class XPathAPI implements Parser {

	@Override
	public String parse(String xmlResponse, String key) {
		List<String> nodeArrayList = new CopyOnWriteArrayList<String>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));

			XPath xPath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xPath.compile("/plist/dict/dict/*/text()");
			NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				nodeArrayList.add(((Node) nodeList.item(i)).getNodeValue());
			}
			if (nodeArrayList.contains(key)) {
				return nodeArrayList.get(nodeArrayList.indexOf(key) + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
