package org.kwok.util.xml;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.kwok.util.xml.tool.XmlToJson;

/**
 * XmlToJson 构建类的测试。
 * 对 XML 标签属性节点的操作。
 * @author Kwok
 */
public class XmlToJsonBuilderTest {

	/*
	<?xml version="1.0" encoding="utf-8"?>
	<Users>
	    <User>
	        <Name>Kwok</Name>
	        <book id="001">数据结构</book>
	        <book id="002">C语言</book>
	        <book id="002">Java</book>
	    </User>
	     <User>
	        <Name>Andy</Name>
	        <book id="007">操作系统</book>
	        <book id="008">数据库</book>
	        <book id="009">计算机组成原理</book>
	    </User>
	 </Users>
	 */
	
	String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Users><User><Name>Kwok</Name><book id=\"001\">数据结构</book><book id=\"002\">C语言</book><book id=\"002\">Java</book></User> <User><Name>Andy</Name><book id=\"007\">操作系统</book><book id=\"008\">数据库</book><book id=\"009\">计算机组成原理</book></User></Users>";
	
	/*@Test*/
	public void xmlToJsonTest() throws IOException{
		
		XmlToJson xmlToJson1 = new XmlToJson.Builder(xmlStr).build();
		System.out.println(xmlToJson1.toString());
		
		XmlToJson xmlToJson2 = new XmlToJson.Builder(IOUtils.toInputStream(xmlStr,"UTF-8"), null).build();
		System.out.println(xmlToJson2.toFormattedString());
		
	}
	
	
	@Test
	public void XmlToJsonAttributeHandlerTest(){
		
		/*
		 * 自定义标签内容节点名
		 */
		XmlToJson xmlToJson1 = new XmlToJson.Builder(xmlStr)
				.setContentName("/Users/User/book", "title")
				.build();
		System.out.println(xmlToJson1.toFormattedString());
		
		//忽略某些标签和属性
		XmlToJson xmlToJson2 = new XmlToJson.Builder(xmlStr)
				.skipTag("/Users/User/Name")
				.skipAttribute("/Users/User/book/id")
				.build();
		System.out.println(xmlToJson2.toFormattedString());
		
	}
	
}
