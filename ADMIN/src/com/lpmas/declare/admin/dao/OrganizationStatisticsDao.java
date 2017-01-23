package com.lpmas.declare.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.lpmas.declare.admin.bean.OrganizationStatisticsBean;
import com.lpmas.declare.admin.config.DeclareMongoConfig;
import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.mongodb.client.MongoCollection;

public class OrganizationStatisticsDao {
	public int insertOrganizationStatistics(OrganizationStatisticsBean bean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_ORGANIZATION_STATISTICS);
		return MongoDB.getInstance().insert(collection, bean);
	}

	public long updateOrganizationStatistics(OrganizationStatisticsBean bean) {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_ORGANIZATION_STATISTICS);
		return MongoDB.getInstance().update(collection, bean);
	}

	public OrganizationStatisticsBean getOrganizationStatisticsByKey(String level, String province, String city,
			String region, String declareYear, boolean isSum, String trainingType, String organizationType)
			throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_ORGANIZATION_STATISTICS);
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("level", level);
		condMap.put("province", province);
		condMap.put("city", city);
		condMap.put("region", region);
		condMap.put("declareYear", declareYear);
		condMap.put("isSum", isSum);
		condMap.put("trainingType", trainingType);
		condMap.put("organizationType", organizationType);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		List<OrganizationStatisticsBean> list = MongoDB.getInstance().getRecordListResult(collection, condMap,
				OrganizationStatisticsBean.class, orderBy);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<OrganizationStatisticsBean> getOrganizationStatisticsListByMap(HashMap<String, Object> condMap)
			throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_ORGANIZATION_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		return MongoDB.getInstance().getRecordListResult(collection, condMap, OrganizationStatisticsBean.class,
				orderBy);
	}
}
