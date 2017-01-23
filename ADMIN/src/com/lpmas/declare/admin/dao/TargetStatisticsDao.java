package com.lpmas.declare.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.lpmas.declare.admin.bean.TargetStatisticsBean;
import com.lpmas.declare.admin.config.DeclareMongoConfig;
import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.mongodb.client.MongoCollection;

public class TargetStatisticsDao {
	public int insertTargetStatistics(TargetStatisticsBean bean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_TARGET_STATISTICS);
		return MongoDB.getInstance().insert(collection, bean);
	}

	public long updateTargetStatistics(TargetStatisticsBean bean) {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_TARGET_STATISTICS);
		return MongoDB.getInstance().update(collection, bean);
	}

	public TargetStatisticsBean getTargetStatisticsByKey(String level, String province, String city, String region,
			String declareYear, boolean isSum) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_TARGET_STATISTICS);
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("level", level);
		condMap.put("province", province);
		condMap.put("city", city);
		condMap.put("region", region);
		condMap.put("declareYear", declareYear);
		condMap.put("isSum", isSum);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		List<TargetStatisticsBean> list = MongoDB.getInstance().getRecordListResult(collection, condMap,
				TargetStatisticsBean.class, orderBy);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<TargetStatisticsBean> getTargetStatisticsListByMap(HashMap<String, Object> condMap) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_TARGET_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		return MongoDB.getInstance().getRecordListResult(collection, condMap, TargetStatisticsBean.class, orderBy);
	}

}
