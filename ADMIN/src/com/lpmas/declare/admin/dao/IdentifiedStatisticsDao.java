package com.lpmas.declare.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.lpmas.declare.admin.bean.IdentifiedStatisticsBean;
import com.lpmas.declare.admin.config.DeclareMongoConfig;
import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.mongodb.client.MongoCollection;

public class IdentifiedStatisticsDao {
	public int insertIdentifiedStatistics(IdentifiedStatisticsBean bean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.IDENTIFIED_STATISTICS);
		return MongoDB.getInstance().insert(collection, bean);
	}

	public long updateIdentifiedStatistics(IdentifiedStatisticsBean bean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.IDENTIFIED_STATISTICS);
		return MongoDB.getInstance().update(collection, bean);
	}

	public IdentifiedStatisticsBean getIdentifiedStatisticsByKey(String id) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.IDENTIFIED_STATISTICS);
		return MongoDB.getInstance().getRecordById(collection, id, IdentifiedStatisticsBean.class);
	}

	public IdentifiedStatisticsBean getIdentifiedStatisticsByKey(String level, String province, String city,
			String region, String declareYear, boolean isSum) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.IDENTIFIED_STATISTICS);
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("level", level);
		condMap.put("province", province);
		condMap.put("city", city);
		condMap.put("region", region);
		condMap.put("declareYear", declareYear);
		condMap.put("isSum", isSum);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		List<IdentifiedStatisticsBean> list = MongoDB.getInstance().getRecordListResult(collection, condMap,
				IdentifiedStatisticsBean.class, orderBy);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public PageResultBean<IdentifiedStatisticsBean> getIdentifiedStatisticsPageListByMap(
			HashMap<String, Object> condMap, PageBean pageBean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.IDENTIFIED_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		return MongoDB.getInstance().getPageListResult(collection, condMap, IdentifiedStatisticsBean.class, pageBean,
				orderBy);
	}

	public List<IdentifiedStatisticsBean> getIdentifiedStatisticsListByMap(HashMap<String, Object> condMap)
			throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.IDENTIFIED_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		return MongoDB.getInstance().getRecordListResult(collection, condMap, IdentifiedStatisticsBean.class, orderBy);
	}

}
