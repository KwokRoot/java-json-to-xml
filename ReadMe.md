# java-json-to-xml

### java-json-to-xml Goals
- JSON TO XML.
- XML TO JSON.
- JSONString TO XMLString.
- JSONString TO FormattedXMLString.
- JSONObject(org.json) TO XMLString.
- XMLString TO JSONString.
- XMLString TO FormattedJSONString.
- XMLString TO JSONObject(org.json).
- Other XML Attribute Node Handle.
- JSON Converter XML.

鸣谢：
[smart-fun/XmlToJson
](https://github.com/smart-fun/XmlToJson "smart-fun/XmlToJson
")
：Android Library for converting XML to JSON and JSON to XML.

***

## Maven Dependencies

```xml
<dependency>
	<groupId>org.json</groupId>
	<artifactId>json</artifactId>
	<version>20180813</version>
</dependency>
```

```xml
<dependency>
	<groupId>xpp3</groupId>
	<artifactId>xpp3</artifactId>
	<version>1.1.4c</version>
</dependency>
```

```xml
<dependency>
	<groupId>commons-io</groupId>
	<artifactId>commons-io</artifactId>
	<version>2.6</version>
</dependency>
```

```xml
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.12</version>
	<scope>test</scope>
</dependency>
```

***

## Usage

***

### XML to JSON

###### 两种方式创建 XmlToJson ######

```java
XmlToJson xmlToJson = new XmlToJson.Builder(xmlString).build();
```

OR

```java
XmlToJson xmlToJson = new XmlToJson.Builder(inputStream, "UTF-8").build();
```

```java
// 转化为 JSONObject
JSONObject jsonObject = xmlToJson.toJson();

// 转化为 JSONString
String jsonString = xmlToJson.toString();

// 转化为格式化的 JSONString
String formattedJSONString = xmlToJson.toFormattedString();
```

###### Example:

```xml
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
```

###### Result:

```json
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
```

### Custom Content names ###

By default, the content of a XML Tag is converted into a key called "content". This name can be changed with a custom one, using **Builder.setContentName**(String contentPath, String replacementName). You can change as many content names as you want.

```xml
<?xml version="1.0" encoding="utf-8"?>
<library>
    <book id="007">James Bond</book>
    <book id="000">Book for the dummies</book>
</library>
```

```java
public String convertXmlToJson(String xml) {
    XmlToJson xmlToJson = new XmlToJson.Builder(xml)
        .setContentName("/library/book", "title")
        .build();
    return xmlToJson.toString();
}
```

```json
{  
   "library":{  
      "book":[  
         {  
            "id":"007",
            "title":"James Bond"
         },
         {  
            "id":"000",
            "title":"Book for the dummies"
         }
      ]
   }
}
```

### Custom Attributes names ###

Attributes are converted into key / values in the JSON. The attribute names may conflict with other keys. You can change the name of any attribute, by specifying the path to the attribute and the replacement name, using **Builder.setAttributeName**(String attributePath, String replacementName).

```xml
<?xml version="1.0" encoding="utf-8"?>
<library>
    <book id="007">James Bond</book>
    <book id="000">Book for the dummies</book>
</library>
```

```java
public String convertXmlToJson(String xml) {
    XmlToJson xmlToJson = new XmlToJson.Builder(xml)
        .setAttributeName("/library/book/id", "code")
        .build();
    return xmlToJson.toString();
}
```

```json
{  
   "library":{  
      "book":[  
         {  
            "code":"007",
            "content":"James Bond"
         },
         {  
            "code":"000",
            "content":"Book for the dummies"
         }
      ]
   }
}
```

### Force a Tag to be a list ###

In a XML hierarchy, an entry can have children. For example, \<library> has 2 entries \<book>. In case there is only one book, there is no way to know that Book is a list. But you can force it using **Builder.forceList**(String path).

```xml
<?xml version="1.0" encoding="utf-8"?>
<library>
    <book id="007">James Bond</book>
</library>
```

By default, the \<book> tag is NOT considered as a list

```json
{  
   "library":{  
      "book":{  
         "id":"007",
         "content":"James Bond"
      }
   }
}
```

```java
public String convertXmlToJson(String xml) {
    XmlToJson xmlToJson = new XmlToJson.Builder(xml)
        .forceList("/library/book")
        .build();
    return xmlToJson.toString();
}
```

Now \<book> is considered as a list:

```json
{  
   "library":{  
      "book":[  
         {  
            "id":"007",
            "content":"James Bond"
         }
      ]
   }
}
```

### Force a Tag or Attribute to be an Integer / Long / Double / Boolean ###

By default the XML attributes or content are processed as Strings. If you want to force them to be another type (like Integer), then use on of these methods **Builder.forceIntegerForPath**(String path), **Builder.forceLongForPath**(String path), **Builder.forceDoubleForPath**(String path) or **Builder.forceBooleanForPath**(String path).

```xml
<?xml version="1.0" encoding="utf-8"?>
<library>
    <owner>John Doe</owner>
    <book id="007">James Bond</book>
    <book id="000">Book for the dummies</book>
</library>
```

```java
public String convertXmlToJson(String xml) {
    XmlToJson xmlToJson = new XmlToJson.Builder(xml)
        .Builder.forceIntegerForPath("/library/book/id")
        .build();
    return xmlToJson.toString();
}
```

```json
{  
   "library":{
      "owner": "John Doe",
      "book":[  
         {  
            "id":7,
            "content":"James Bond"
         },
         {  
            "id":0,
            "content":"Book for the dummies"
         }
      ]
   }
}
```
Here "007" and "000" are converted to 7 and 0.

Note that you can use forcexxxForPath methods AND change the attribute or content name for the same path; the methods in the Builder can be combined. The path used in forcexxxForPath methods is the path in the xml before eventually changing its name.

### Skip a Tag or an Attribute ###

If you are not interrested in getting all the content of the XML, you can skip some Tags or some Attributes. Like for other methods you have to provide the path for the element to skip. You can use **skipTag** and **skipAttribute** as many times as you need.

```xml
<?xml version="1.0" encoding="utf-8"?>
<library>
    <owner>John Doe</owner>
    <book id="007">James Bond</book>
    <book id="000">Book for the dummies</book>
</library>
```

```java
XmlToJson xmlToJson = new XmlToJson.Builder(xml)
    .skipTag("/library/owner")
    .skipAttribute("/library/book/id")
    .build();    
```

```json
{  
   "library":{  
      "book":[  
         {  
            "content":"James Bond"
         },
         {  
            "content":"Book for the dummies"
         }
      ]
   }
}
```


### JSON to XML

###### 三种方式创建 JsonToXml ######

```java
JSONObject jsonObject;
JsonToXml jsonToXml = new JsonToXml.Builder(jsonObject).build();
```

OR

```java
String jsonString;
JsonToXml jsonToXml = new JsonToXml.Builder(jsonString).build();
```

OR

```java
JsonToXml jsonToXml = new JsonToXml.Builder(inputStream).build();
```

Then you can convert it to a XML String or a XML Formatted String (with indentation and line breaks)

```java

// 转化为 XMLString
String xmlString = jsonToXml.toString();

// 转化为格式化的 XMLString
int indentationSize = 3;
String formattedXmlString = jsonToXml.toFormattedString(indentationSize);
```

###### Example:

```json
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
```

###### Result:

```xml
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
```

### Force a TAG to be an parent Attribute ###

You may want to use XML Attributes instead of TAG content. You can do this by using the **forceAttribute** method. You need to specify the Path to the TAG.


```json
{
    "owner": {
        "id": 124,
        "name": "John Doe"
    }
}
```

```java
JsonToXml jsonToXml = new JsonToXml.Builder(jsonObject)
        .forceAttribute("/owner/id")
        .build();
```

The result becomes

```xml
<?xml version="1.0" encoding="UTF-8"?>
<owner id="124">
    <name>John Doe</name>
</owner>
```

### Force a TAG to be a parent Content ###

When a Tag has only one child, you may want that child to be the Content for its parent. You can use the **forceContent** method to achieve this.

```java
JsonToXml jsonToXml = new JsonToXml.Builder(jsonObject)
        .forceAttribute("/owner/id")
        .forceContent("/owner/name")
        .build();
```

The result becomes

```xml
<?xml version="1.0" encoding="UTF-8"?>
<owner id="124">John Doe</owner>
```



***





## Package

```
│  .classpath
│  .gitignore
│  .project
│  LICENSE
│  pom.xml
│  ReadMe.md
│
├─.settings
│      org.eclipse.core.resources.prefs
│      org.eclipse.jdt.core.prefs
│      org.eclipse.m2e.core.prefs
│      org.eclipse.wst.common.project.facet.core.xml
│
├─src
│  ├─main
│  │  ├─java
│  │  │  └─org
│  │  │      └─kwok
│  │  │          └─util
│  │  │              └─xml
│  │  │                  │  XmlParserUtils.java
│  │  │                  │
│  │  │                  └─tool
│  │  │                          IOUtil.java
│  │  │                          JsonToXml.java
│  │  │                          Node.java
│  │  │                          SpecialCharacterHandler.java
│  │  │                          Tag.java
│  │  │                          XmlToJson.java
│  │  │
│  │  └─resources
│  │          test.json
│  │          test.xml
│  │          test1.json
│  │          test1.xml
│  │          test2.json
│  │          test2.xml
│  │
│  └─test
│      └─java
│          └─org
│              └─kwok
│                  └─util
│                      └─xml
│                              JsonToXmlBuilderTest.java
│                              XmlParserUtilsTest.java
│                              XmlToJsonBuilderTest.java
│
└─target
```

***
***
***

### *License*

java-json-to-properties is released under the [Apache 2.0 license](LICENSE).

```
Copyright 2018-2019 DreamLead Group Kwok(AndyKuo).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```