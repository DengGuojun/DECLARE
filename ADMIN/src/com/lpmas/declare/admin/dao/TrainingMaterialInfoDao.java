package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.TrainingMaterialInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TrainingMaterialInfoDao {
	private static Logger log = LoggerFactory.getLogger(TrainingMaterialInfoDao.class);

	public int insertTrainingMaterialInfo(TrainingMaterialInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_material_info ( material_name, material_type, training_year, province, city, region, compile_organization, publishing_company, publishing_yeah, publishing_month, word_quantity, paper_quantity, price, industry, link, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMaterialName());
			ps.setString(2, bean.getMaterialType());
			ps.setString(3, bean.getTrainingYear());
			ps.setString(4, bean.getProvince());
			ps.setString(5, bean.getCity());
			ps.setString(6, bean.getRegion());
			ps.setString(7, bean.getCompileOrganization());
			ps.setString(8, bean.getPublishingCompany());
			ps.setString(9, bean.getPublishingYeah());
			ps.setString(10, bean.getPublishingMonth());
			ps.setInt(11, bean.getWordQuantity());
			ps.setInt(12, bean.getPaperQuantity());
			ps.setDouble(13, bean.getPrice());
			ps.setString(14, bean.getIndustry());
			ps.setString(15, bean.getLink());
			ps.setInt(16, bean.getStatus());
			ps.setInt(17, bean.getCreateUser());
			ps.setString(18, bean.getMemo());

			result = db.executePstmtInsert();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}
	public int insertTrainingMaterialInfoWithCreateTime(TrainingMaterialInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_material_info ( material_name, material_type, training_year, province, city, region, compile_organization, publishing_company, publishing_yeah, publishing_month, word_quantity, paper_quantity, price, industry, link, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMaterialName());
			ps.setString(2, bean.getMaterialType());
			ps.setString(3, bean.getTrainingYear());
			ps.setString(4, bean.getProvince());
			ps.setString(5, bean.getCity());
			ps.setString(6, bean.getRegion());
			ps.setString(7, bean.getCompileOrganization());
			ps.setString(8, bean.getPublishingCompany());
			ps.setString(9, bean.getPublishingYeah());
			ps.setString(10, bean.getPublishingMonth());
			ps.setInt(11, bean.getWordQuantity());
			ps.setInt(12, bean.getPaperQuantity());
			ps.setDouble(13, bean.getPrice());
			ps.setString(14, bean.getIndustry());
			ps.setString(15, bean.getLink());
			ps.setInt(16, bean.getStatus());
			ps.setTimestamp(17, bean.getCreateTime());
			ps.setInt(18, bean.getCreateUser());
			ps.setString(19, bean.getMemo());
			
			result = db.executePstmtInsert();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public int updateTrainingMaterialInfo(TrainingMaterialInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update training_material_info set material_name = ?, material_type = ?, training_year = ?, province = ?, city = ?, region = ?, compile_organization = ?, publishing_company = ?, publishing_yeah = ?, publishing_month = ?, word_quantity = ?, paper_quantity = ?, price = ?, industry = ?, link = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where material_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMaterialName());
			ps.setString(2, bean.getMaterialType());
			ps.setString(3, bean.getTrainingYear());
			ps.setString(4, bean.getProvince());
			ps.setString(5, bean.getCity());
			ps.setString(6, bean.getRegion());
			ps.setString(7, bean.getCompileOrganization());
			ps.setString(8, bean.getPublishingCompany());
			ps.setString(9, bean.getPublishingYeah());
			ps.setString(10, bean.getPublishingMonth());
			ps.setInt(11, bean.getWordQuantity());
			ps.setInt(12, bean.getPaperQuantity());
			ps.setDouble(13, bean.getPrice());
			ps.setString(14, bean.getIndustry());
			ps.setString(15, bean.getLink());
			ps.setInt(16, bean.getStatus());
			ps.setInt(17, bean.getModifyUser());
			ps.setString(18, bean.getMemo());

			ps.setInt(19, bean.getMaterialId());

			result = db.executePstmtUpdate();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public TrainingMaterialInfoBean getTrainingMaterialInfoByKey(int materialId) {
		TrainingMaterialInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_material_info where material_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, materialId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingMaterialInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingMaterialInfoBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			bean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public PageResultBean<TrainingMaterialInfoBean> getTrainingMaterialInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TrainingMaterialInfoBean> result = new PageResultBean<TrainingMaterialInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_material_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String materialType = condMap.get("materialType"); // 教材种类
			if (StringKit.isValid(materialType)) {
				condList.add("material_type = ?");
				paramList.add(materialType);
			}

			String industry = condMap.get("industry"); // 教材种类
			if (StringKit.isValid(industry)) {
				condList.add("industry  like ? ");
				paramList.add("%" + industry + "%");
			}

			String trainingYear = condMap.get("trainingYear"); // 培训年份
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}

			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("province = ?");
				paramList.add(province);
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("city = ?");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}

			String materialName = condMap.get("materialName"); // 教材名字
			if (StringKit.isValid(materialName)) {
				condList.add("material_name like ?");
				paramList.add("%" + materialName + "%");
			}

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by material_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TrainingMaterialInfoBean.class,
					pageBean, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}
	
	public int getTrainingMaterialCountByMap(Map<String, String> condMap){
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select 1 from training_material_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("province = ?");
				paramList.add(province);
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("city = ?");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String beginTime = condMap.get("beginTime");
			if (StringKit.isValid(beginTime)) {
				condList.add("create_time >= ?");
				paramList.add(beginTime);
			}
			String endTime = condMap.get("endTime");
			if (StringKit.isValid(endTime)) {
				condList.add("create_time <= ?");
				paramList.add(endTime);
			}
			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getTotalRecordResult(sql, condList, paramList,db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	// 获取是否存在指定的教材信息
	public TrainingMaterialInfoBean getTrainingMaterialInfo(TrainingMaterialInfoBean bean) {
		TrainingMaterialInfoBean trainingMaterialInfoBean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_material_info where material_name = ? and country = ? and  province = ? and city = ? and region = ? and training_year = ?   and status = ?  ";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMaterialName());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getRegion());
			ps.setString(6, bean.getTrainingYear());
			ps.setInt(7, Constants.STATUS_VALID); // 暂时设定为存在数据库的状态。

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				trainingMaterialInfoBean = new TrainingMaterialInfoBean();
				trainingMaterialInfoBean = BeanKit.resultSet2Bean(rs, TrainingMaterialInfoBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			trainingMaterialInfoBean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return trainingMaterialInfoBean;
	}

	public List<TrainingMaterialInfoBean> getTrainingMaterialListByMap(HashMap<String, String> condMap) {
		List<TrainingMaterialInfoBean> result = new ArrayList<TrainingMaterialInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from Training_material_Info";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String orderQuery = "order by material_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList,
					TrainingMaterialInfoBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public List<TrainingMaterialInfoBean> getAllTrainingMaterial(HashMap<String, String> condMap) {
		List<TrainingMaterialInfoBean> result = new ArrayList<TrainingMaterialInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_material_info ";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by material_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList,
					TrainingMaterialInfoBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}
}
