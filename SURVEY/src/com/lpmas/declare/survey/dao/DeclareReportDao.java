package com.lpmas.declare.survey.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.survey.config.DeclareMongoConfig;
import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.nosql.mongodb.MongoDBConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.mongodb.client.MongoCollection;

public class DeclareReportDao {
	public int insertDeclareReport(DeclareReportBean bean) {
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
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("declareId", MongoDBConfig.SORT_ORDER_ASC);
		return MongoDB.getInstance().getPageListResult(collection, condMap, DeclareReportBean.class, pageBean, orderBy);
	}

	public List<DeclareReportBean> getDeclareReportListByMap(HashMap<String, Object> condMap) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.COLLECTION_DECLARE_REPORT);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		orderBy.put("declareId", MongoDBConfig.SORT_ORDER_ASC);
		return MongoDB.getInstance().getRecordListResult(collection, condMap, DeclareReportBean.class, orderBy);
	}
}
