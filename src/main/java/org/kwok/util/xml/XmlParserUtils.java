package org.kwok.util.xml;

import org.json.JSONObject;
import org.kwok.util.xml.tool.JsonToXml;
import org.kwok.util.xml.tool.XmlToJson;

/**
 * XML 解析工具类：<br>
 * 1.XML 字符串转化为 JSON 字符串<br>
 * 2.XML 字符串转化为格式化的 JSON 字符串<br>
 * 3.XML 字符串转化为 JSONObject(org.json)对象<br>
 * 4.JSON 字符串转化为 XML 字符串<br>
 * 5.JSON 字符串转化为格式化的 XML 字符串<br>
 * 6.JSONObject(org.json)对象转化为 XML 字符串<br>
 * @author Kwok
 */
public class XmlParserUtils {
	
	/**
	 * @Description: XML 字符串转化为 JSON 字符串
	 * @methods: xmlStrToJsonStr
	 * @param xmlStr XML字符串
	 * @return String
	 * @author Kwok
	 */
	public static String xmlStrToJsonStr(String xmlStr){
		if (xmlStr == null || "".equals(xmlStr)) {
			return "";
		}
		XmlToJson xmlToJson = new XmlToJson.Builder(xmlStr).build();
		return xmlToJson.toString();
	}
	
	
	/**
	 * @Description: XML 字符串转化为格式化的 JSON 字符串
	 * @methods: xmlStrToFormattedJsonStr
	 * @param xmlStr XML字符串
	 * @param indentationStr 间隔字符串：一般设置为：" "、 or "\t"、如果设置 null，默认为 3 个空格间隙。
	 * @return String
	 * @author Kwok
	 */
	public static String xmlStrToFormattedJsonStr(String xmlStr, String indentationStr){
		if (xmlStr == null || "".equals(xmlStr)) {
			return "";
		}
		XmlToJson xmlToJson = new XmlToJson.Builder(xmlStr).build();
		return xmlToJson.toFormattedString(indentationStr);
	}
	
	
	/**
	 * @Description: XML 字符串转化为 JSONObject(org.json)对象
	 * @methods: xmlStrToJSONObject
	 * @param xmlStr
	 * @return JSONObject(org.json)
	 * @author Kwok
	 */
	public static JSONObject xmlStrToJSONObject(String xmlStr){
		if (xmlStr == null || "".equals(xmlStr)) {
			return null;
		}
		XmlToJson xmlToJson = new XmlToJson.Builder(xmlStr).build();
		return xmlToJson.toJson();
	}
	
	
	/**
	 * @Description: JSON 字符串转化为 XML 字符串
	 * @methods: jsonStrToXmlStr
	 * @param jsonStr
	 * @return String
	 * @author Kwok
	 */
	public static String jsonStrToXmlStr(String jsonStr){
		if (jsonStr == null || "".equals(jsonStr)) {
			return "";
		}
		JSONObject jsonObject = new JSONObject(jsonStr);
		JsonToXml jsonToXml = new JsonToXml.Builder(jsonObject).build();
		return jsonToXml.toString();
	}
	
	
	/**
	 * @Description: JSON 字符串转化为格式化的 XML 字符串
	 * @methods: jsonStrToFormattedXmlStr
	 * @param jsonStr
	 * @param indentationSize 缩进字符间隔，默认为 3
	 * @return String
	 * @author Kwok
	 */
	public static String jsonStrToFormattedXmlStr(String jsonStr, Integer indentationSize){
		if (jsonStr == null || "".equals(jsonStr)) {
			return "";
		}
		JSONObject jsonObject = new JSONObject(jsonStr);
		JsonToXml jsonToXml = new JsonToXml.Builder(jsonObject).build();
		return jsonToXml.toFormattedString(indentationSize);
	}
	
	
	/**
	 * @Description: JSONObject(org.json)对象转化为 XML 字符串
	 * @methods: JSONObjectToXmlStr
	 * @param jsonObject
	 * @param indentationSize
	 * @return String
	 * @date: 2019年2月1日
	 * @author Kwok
	 */
	public static String JSONObjectToXmlStr(JSONObject jsonObject, Integer indentationSize){
		if(jsonObject ==null || !(jsonObject instanceof JSONObject)){
			return "";
		}
		JsonToXml jsonToXml = new JsonToXml.Builder(jsonObject).build();
		return jsonToXml.toFormattedString(indentationSize);
	}
	
}
