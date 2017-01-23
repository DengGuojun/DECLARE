package com.lpmas.declare.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.lpmas.declare.admin.bean.TeacherStatisticsBean;
import com.lpmas.declare.admin.config.DeclareMongoConfig;
import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.mongodb.client.MongoCollection;

public class TeacherStatisticsDao {
	public int insertTeacherStatistics(TeacherStatisticsBean bean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.TEACHER_STATISTICS);
		return MongoDB.getInstance().insert(collection, bean);
	}

	public long updateTeacherStatistics(TeacherStatisticsBean bean) {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.TEACHER_STATISTICS);
		return MongoDB.getInstance().update(collection, bean);
	}
	 

	public TeacherStatisticsBean getTeacherStatisticsByKey(String id) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.TEACHER_STATISTICS);
		return MongoDB.getInstance().getRecordById(collection, id, TeacherStatisticsBean.class);
	}
	
	public TeacherStatisticsBean getTeacherStatisticsByKey(String level, boolean sum, String province, String city, String region) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.TEACHER_STATISTICS);
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("level", level);
		condMap.put("sum", sum);
		condMap.put("province", province);
		condMap.put("city", city);
		condMap.put("region", region);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		List<TeacherStatisticsBean> list = MongoDB.getInstance().getRecordListResult(collection, condMap, TeacherStatisticsBean.class, orderBy);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public PageResultBean<TeacherStatisticsBean> getTeacherStatisticsPageListByMap(HashMap<String, Object> condMap,
			PageBean pageBean) throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.TEACHER_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		// orderBy.put("_id", MongoDBConfig.SORT_ORDER_ASC);
		return MongoDB.getInstance().getPageListResult(collection, condMap, TeacherStatisticsBean.class, pageBean,
				orderBy);
	}
	 
	public List<TeacherStatisticsBean> getTeacherStatisticsListByMap(HashMap<String, Object> condMap)
			throws Exception {
		MongoCollection<Document> collection = MongoDB.getInstance().getCollection(DeclareMongoConfig.DB_NAME,
				DeclareMongoConfig.TEACHER_STATISTICS);
		Map<String, Object> orderBy = new HashMap<String, Object>();
		// orderBy.put("_id", MongoDBConfig.SORT_ORDER_ASC);
		return MongoDB.getInstance().getRecordListResult(collection, condMap, TeacherStatisticsBean.class, orderBy);
	}
	
}
