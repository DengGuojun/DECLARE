package com.lpmas.declare.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.lpmas.declare.admin.bean.DeclareStatisticsBean;
import com.lpmas.declare.admin.config.DeclareMongoConfig;
import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.mongodb.client.MongoCollection;

public class DeclareStatisticsDao {
	public int insertDeclareStatistics(DeclareStatisticsBean bean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_STATISTICS);
		return MongoDB.getInstance().insert(collection, bean);
	}

	public long updateDeclareStatistics(DeclareStatisticsBean bean) {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_STATISTICS);
		return MongoDB.getInstance().update(collection, bean);
	}
	 

	public DeclareStatisticsBean getDeclareStatisticsByKey(String id) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_STATISTICS);
		return MongoDB.getInstance().getRecordById(collection, id, DeclareStatisticsBean.class);
	}
	
	public DeclareStatisticsBean getDeclareStatisticsByKey(String level, boolean sum, String province, String city, String region, String declareYear) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_STATISTICS);
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("level", level);
		condMap.put("sum", sum);
		condMap.put("province", province);
		condMap.put("city", city);
		condMap.put("region", region);
		condMap.put("declareYear", declareYear);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		List<DeclareStatisticsBean> list = MongoDB.getInstance().getRecordListResult(collection, condMap, DeclareStatisticsBean.class, orderBy);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public PageResultBean<DeclareStatisticsBean> getDeclareStatisticsPageListByMap(HashMap<String, Object> condMap,
			PageBean pageBean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		// orderBy.put("_id", MongoDBConfig.SORT_ORDER_ASC);
		return MongoDB.getInstance().getPageListResult(collection, condMap, DeclareStatisticsBean.class, pageBean,
				orderBy);
	}
	 
	public List<DeclareStatisticsBean> getDeclareStatisticsListByMap(HashMap<String, Object> condMap)
			throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.DECLARE_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		// orderBy.put("_id", MongoDBConfig.SORT_ORDER_ASC);
		return MongoDB.getInstance().getRecordListResult(collection, condMap, DeclareStatisticsBean.class, orderBy);
	}
	
}
