package com.lpmas.declare.console.config;

import java.io.InputStreamReader;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lpmas.framework.cache.memcached.MemcachedPool;

public class DeclareMongoConfig {

	public static String DB_NAME = "declare";

	public static final String COLLECTION_DECLARE_REPORT = "declare_report";

	static {
		initDbName();
	}

	private static void initDbName() {
		try {
			InputStreamReader in = new InputStreamReader(
					MemcachedPool.class.getResourceAsStream("/config/mongodb.xml"));
			SAXReader reader = new SAXReader();
			Document document = reader.read(in);
			Element root = document.getRootElement();
			Iterator<Element> iter = root.elementIterator("mongodb");
			if (iter.hasNext()) {
				Element config = iter.next();
				DB_NAME = config.elementText("db_name");
			}
			in.close();
		} catch (Exception e) {
		}
	}
}
