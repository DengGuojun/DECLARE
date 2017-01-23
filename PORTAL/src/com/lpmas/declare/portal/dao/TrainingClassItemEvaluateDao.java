package com.lpmas.declare.portal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.TrainingClassItemEvaluateBean;
import com.lpmas.declare.portal.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TrainingClassItemEvaluateDao {
	private static Logger log = LoggerFactory.getLogger(TrainingClassItemEvaluateDao.class);

	public int insertTrainingClassItemEvaluate(TrainingClassItemEvaluateBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_class_item_evaluate ( item_id, class_id, declare_id, evaluate_score, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getItemId());
			ps.setInt(2, bean.getClassId());
			ps.setInt(3, bean.getDeclareId());
			ps.setDouble(4, bean.getEvaluateScore());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getCreateUser());
			ps.setString(7, bean.getMemo());

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

	public int updateTrainingClassItemEvaluate(TrainingClassItemEvaluateBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update training_class_item_evaluate set item_id = ?, class_id = ?, declare_id = ?, evaluate_score = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where evaluate_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getItemId());
			ps.setInt(2, bean.getClassId());
			ps.setInt(3, bean.getDeclareId());
			ps.setDouble(4, bean.getEvaluateScore());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getModifyUser());
			ps.setString(7, bean.getMemo());

			ps.setInt(8, bean.getEvaluateId());

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

	public TrainingClassItemEvaluateBean getTrainingClassItemEvaluateByKey(int evaluateId) {
		TrainingClassItemEvaluateBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_item_evaluate where evaluate_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, evaluateId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassItemEvaluateBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingClassItemEvaluateBean.class);
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

	public PageResultBean<TrainingClassItemEvaluateBean> getTrainingClassItemEvaluatePageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TrainingClassItemEvaluateBean> result = new PageResultBean<TrainingClassItemEvaluateBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_item_evaluate";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by evaluate_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList,
					TrainingClassItemEvaluateBean.class, pageBean, db);
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
	
	public List<TrainingClassItemEvaluateBean> getTrainingClassItemEvaluateListByMap(HashMap<String, String> condMap) {
		List<TrainingClassItemEvaluateBean> list = new ArrayList<TrainingClassItemEvaluateBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_item_evaluate";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String classId = condMap.get("classId");
			if (StringKit.isValid(classId)) {
				condList.add("class_id = ?");
				paramList.add(classId);
			}
			String itemId = condMap.get("itemId");
			if (StringKit.isValid(itemId)) {
				condList.add("item_id = ?");
				paramList.add(itemId);
			}
			String evaluateType = condMap.get("evaluateType");
			if (StringKit.isValid(evaluateType)) {
				condList.add("evaluate_type = ?");
				paramList.add(evaluateType);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String orderQuery = "order by evaluate_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList,
					TrainingClassItemEvaluateBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}

}
