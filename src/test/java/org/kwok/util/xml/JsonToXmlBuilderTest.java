package org.kwok.util.xml;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.kwok.util.xml.tool.JsonToXml;

/**
 * JsonToXml 构建类的测试。
 * 对 XML 标签属性节点的操作。
 * @author Kwok
 */
public class JsonToXmlBuilderTest {

	/*
	{
	   "Users": {
	      "User": [
	         {
	            "book": [
	               {
	                  "id": "001",
	                  "content": "数据结构"
	               },
	               {
	                  "id": "002",
	                  "content": "C语言"
	               },
	               {
	                  "id": "002",
	                  "content": "Java"
	               }
	            ],
	            "Name": "Kwok"
	         },
	         {
	            "book": [
	               {
	                  "id": "007",
	                  "content": "操作系统"
	               },
	               {
	                  "id": "008",
	                  "content": "数据库"
	               },
	               {
	                  "id": "009",
	                  "content": "计算机组成原理"
	               }
	            ],
	            "Name": "Andy"
	         }
	      ]
	   }
	}
	*/
	
	static String jsonStr = "{\"Users\":{\"User\":[{\"book\":[{\"id\":\"001\",\"content\":\"数据结构\"},{\"id\":\"002\",\"content\":\"C语言\"},{\"id\":\"002\",\"content\":\"Java\"}],\"Name\":\"Kwok\"},{\"book\":[{\"id\":\"007\",\"content\":\"操作系统\"},{\"id\":\"008\",\"content\":\"数据库\"},{\"id\":\"009\",\"content\":\"计算机组成原理\"}],\"Name\":\"Andy\"}]}}";
	
	@Test
	public void JsonToXmlTest() throws IOException{
		
		JsonToXml jsonToXml1 = new JsonToXml.Builder(new JSONObject(jsonStr)).build();
		System.out.println(jsonToXml1.toFormattedString());
		
		JsonToXml jsonToXml2 = new JsonToXml.Builder(jsonStr).build();
		System.out.println(jsonToXml2.toFormattedString());
		
		JsonToXml jsonToXml3 = new JsonToXml.Builder(IOUtils.toInputStream(jsonStr, "UTF-8")).build();
		System.out.println(jsonToXml3.toFormattedString());
		
	}
	
	@Test
	public void JsonToXmlAttributeHandlerTest(){
		
		/*
		 * 把某些标签节点设置为属性和内容
		 */
		JsonToXml jsonToXml = new JsonToXml.Builder(jsonStr)
				.forceAttribute("/Users/User/book/id")
				.forceContent("/Users/User/book/content")
				.build();
		System.out.println(jsonToXml.toFormattedString());
		
		/*
		设置前结果：
		<Users>
		   <User>
		      <book>
		         <id>001</id>
		         <content>数据结构</content>
		      </book>
		      <book>
		         <id>002</id>
		         <content>C语言</content>
		      </book>
		      <book>
		         <id>002</id>
		         <content>Java</content>
		      </book>
		      <Name>Kwok</Name>
		   </User>
		   <User>
		      <book>
		         <id>007</id>
		         <content>操作系统</content>
		      </book>
		      <book>
		         <id>008</id>
		         <content>数据库</content>
		      </book>
		      <book>
		         <id>009</id>
		         <content>计算机组成原理</content>
		      </book>
		      <Name>Andy</Name>
		   </User>
		</Users>

		
		设置后结果：
	 	<Users>
		   <User>
		      <book id="001">数据结构</book>
		      <book id="002">C语言</book>
		      <book id="002">Java</book>
		      <Name>Kwok</Name>
		   </User>
		   <User>
		      <book id="007">操作系统</book>
		      <book id="008">数据库</book>
		      <book id="009">计算机组成原理</book>
		      <Name>Andy</Name>
		   </User>
		</Users>
		*/
	
	}
}
