package org.kwok.util.xml;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Test;

/**
 * XmlParserUtils 测试类
 * @author Kwok
 */
public class XmlParserUtilsTest {

	@Test
	public void xmlToJsonTest() throws IOException{
		System.out.println(XmlParserUtils.xmlStrToJsonStr(FileUtils.readFileToString(new File("src/main/resources/test.xml"), "UTF-8")));
		
		System.out.println(XmlParserUtils.xmlStrToFormattedJsonStr(FileUtils.readFileToString(new File("src/main/resources/test1.xml"), "UTF-8"), null));
		
		System.out.println(XmlParserUtils.xmlStrToJSONObject(FileUtils.readFileToString(new File("src/main/resources/test2.xml"), "UTF-8")));
	}
	
	
	@Test
	public void jsonToXmlTest() throws IOException{
		System.out.println(XmlParserUtils.jsonStrToXmlStr(FileUtils.readFileToString(new File("src/main/resources/test.json"), "UTF-8")));
		
		System.out.println(XmlParserUtils.jsonStrToFormattedXmlStr(FileUtils.readFileToString(new File("src/main/resources/test1.json"), "UTF-8"), null));
		
		System.out.println(XmlParserUtils.JSONObjectToXmlStr(new JSONObject(FileUtils.readFileToString(new File("src/main/resources/test2.json"), "UTF-8")), null));
	}
	
}
