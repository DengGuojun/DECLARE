package com.lpmas.declare.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import com.lpmas.declare.admin.config.DeclareMongoConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.nosql.mongodb.MongoDBConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DeclareReportDao {
	public int insertDeclareReport(DeclareReportBean bean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.COLLECTION_DECLARE_REPORT);
		return MongoDB.getInstance().insert(collection, bean);
	}

	public long updateDeclareReport(DeclareReportBean bean) {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.COLLECTION_DECLARE_REPORT);
		return MongoDB.getInstance().update(collection, bean);
	}

	public DeclareReportBean getDeclareReportByKey(String id) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.COLLECTION_DECLARE_REPORT);
		return MongoDB.getInstance().getRecordById(collection, id, DeclareReportBean.class);
	}

	public PageResultBean<DeclareReportBean> getDeclareReportPageListByMap(HashMap<String, Object> condMap,
			PageBean pageBean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.COLLECTION_DECLARE_REPORT);
		MongoDB mongoDB = MongoDB.getInstance();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("declareId", MongoDBConfig.SORT_ORDER_DESC);
		BasicDBObject searchCondition = mongoDB.toBson(condMap);
		// return MongoDB.getInstance().getPageListResult(collection, condMap,
		// DeclareReportBean.class, pageBean, orderBy);
		
		PageResultBean<DeclareReportBean> resultBean = new PageResultBean<DeclareReportBean>();
		List<Bson> condList = new ArrayList<Bson>();
		condList.add(new BasicDBObject("$match", mongoDB.toBson(condMap)));
		condList.add(new BasicDBObject("$sort", mongoDB.toBson(orderBy)));
		condList.add(new BasicDBObject("$skip", pageBean.getStartRecordNumber()));
		condList.add(new BasicDBObject("$limit", pageBean.getPageSize()));

		int total = mongoDB.getTotalRecordResult(collection, searchCondition);
		resultBean.setTotalRecordNumber(total);
									
		List<DeclareReportBean> list = new ArrayList<DeclareReportBean>();
		if (total > 0) {
			MongoCursor<Document> cursor = null;
			cursor = collection.aggregate(condList).allowDiskUse(true).iterator();
			while (cursor != null && cursor.hasNext()) {
				Document document = cursor.next();
				String json = document.toJson(new JsonWriterSettings(JsonMode.STRICT));
				DeclareReportBean bean = mongoDB.toBean(json, DeclareReportBean.class);
				list.add(bean);
			}
			cursor.close();
		}
		resultBean.setRecordList(list);
		return resultBean;
	}

	public List<DeclareReportBean> getDeclareReportListByMap(HashMap<String, Object> condMap) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.COLLECTION_DECLARE_REPORT);
		MongoDB mongoDB = MongoDB.getInstance();
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("declareId", MongoDBConfig.SORT_ORDER_ASC);
//		return MongoDB.getInstance().getRecordListResult(collection, condMap, DeclareReportBean.class, orderBy);
		List<Bson> condList = new ArrayList<Bson>();
		condList.add(new BasicDBObject("$match", mongoDB.toBson(condMap)));
		condList.add(new BasicDBObject("$sort", mongoDB.toBson(orderBy)));

		List<DeclareReportBean> list = new ArrayList<DeclareReportBean>();
			MongoCursor<Document> cursor = null;
			cursor = collection.aggregate(condList).allowDiskUse(true).iterator();
			while (cursor != null && cursor.hasNext()) {
				Document document = cursor.next();
				String json = document.toJson(new JsonWriterSettings(JsonMode.STRICT));
				DeclareReportBean bean = mongoDB.toBean(json, DeclareReportBean.class);
				list.add(bean);
			}
			cursor.close();
		return list;
	}

	public int getDeclareReportCountByMap(HashMap<String, Object> condMap) {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.COLLECTION_DECLARE_REPORT);
		return (int) collection.count(MongoDB.getInstance().toBson(condMap));
	}
}
