package org.kwok.util.xml.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

/**
 * 解决运行时异常："javax.xml.transform.TransformerException: org.xml.sax.SAXParseException;"。
 * 解决 SAXParse 解析的 XML 元素名(标签)、属性名有特殊字符的问题。
 * 解决方案：解析前先用 UUID 字符串替换特殊字符，解析后再替换回来。可以根据需要增加替换字符。
 * @author Kwok
 * @date: 20190201
 */
public class SpecialCharacterHandler {

	private static Map<String, String> specialCharacterReplaceMap = new HashMap<String, String>();
	
	static {
		specialCharacterReplaceMap.put(":", UUID.randomUUID().toString());
		/*
		specialCharacterReplaceMap.put("&", UUID.randomUUID().toString());
		specialCharacterReplaceMap.put("#", UUID.randomUUID().toString());
		specialCharacterReplaceMap.put("$", UUID.randomUUID().toString());
		specialCharacterReplaceMap.put("@", UUID.randomUUID().toString());
		specialCharacterReplaceMap.put("%", UUID.randomUUID().toString());
		*/
	}
	
	public static String doReplace(String srcStr){
		String returnStr = null;
		Set<Entry<String, String>> entries = specialCharacterReplaceMap.entrySet();
		for (Entry<String, String> entry : entries) {
			returnStr = srcStr.replaceAll(entry.getKey(), entry.getValue());
		}
		return returnStr;
	}
	
	public static String doRestore(String srcStr){
		String returnStr = null;
		Set<Entry<String, String>> entries = specialCharacterReplaceMap.entrySet();
		for (Entry<String, String> entry : entries) {
			returnStr = srcStr.replaceAll(entry.getValue(), entry.getKey());
		}
		return returnStr;
	}
	
}
