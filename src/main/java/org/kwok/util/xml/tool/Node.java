package org.kwok.util.xml.tool;

/*
    Copyright 2016 Arnaud Guyon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

import java.util.ArrayList;

/**
 * Used to store data when converting from JSON to XML
 */

class Node {

	class Attribute {
		String mKey;
		String mValue;
		Attribute(String key, String value) {
			mKey = key;
			mValue = value;
		}
	}
	
	private String mName;
	private String mPath;
	private String mContent;
	private ArrayList<Attribute> mAttributes = new ArrayList<Attribute>();
	private ArrayList<Node> mChildren = new ArrayList<Node>();

	Node(String name, String path) {
		mName = name;
		mPath = path;
	}

	void addAttribute(String key, String value) {
		mAttributes.add(new Attribute(key, value));
	}

	void setContent(String content) {
		mContent = content;
	}

	void setName(String name) {
		mName = name;
	}

	void addChild(Node child) {
		mChildren.add(child);
	}

	ArrayList<Attribute> getAttributes() {
		return mAttributes;
	}

	String getContent() {
		return mContent;
	}

	ArrayList<Node> getChildren() {
		return mChildren;
	}

	String getPath() {
		return mPath;
	}

	String getName() {
		return mName;
	}
	
}
