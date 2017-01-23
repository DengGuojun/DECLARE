package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.MessageGroupBean;
import com.lpmas.declare.admin.bean.MessageGroupUserBean;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.MessageGroupBusiness;
import com.lpmas.declare.admin.business.MessageGroupUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageGroupSelect
 */
@WebServlet("/declare/admin/MessageGroupSelect.do")
public class MessageGroupSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTACT = "通讯录";
	private static final String CUSTOM = "组";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageGroupSelect() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		MessageGroupBusiness groupBusiness = new MessageGroupBusiness();
		MessageGroupUserBusiness groupUserBusiness = new MessageGroupUserBusiness();
		AdminUserGroupBusiness userGroupBusiness = new AdminUserGroupBusiness();
		AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();

		int id = ParamKit.getIntParameter(request, "id", 0);
		String name = ParamKit.getParameter(request, "name", "");

		List<MessageGroupTreeNode> list = new ArrayList<MessageGroupTreeNode>();
		if (id == 0 && !StringKit.isValid(name)) {
			// 首次加载的根节点
			MessageGroupTreeNode node1 = new MessageGroupTreeNode();
			node1.setId(0);
			node1.setName(CONTACT);
			node1.setIsParent(String.valueOf(true));
			list.add(node1);
			List<MessageGroupBean> result = groupBusiness.getMessageGroupByOwnerId(adminHelper.getAdminUserId());
			for (MessageGroupBean groupBean : result) {
				list.add(groupBean2TreeNode(groupBean));
			}
		} else if (id == 0 && name.equals(CONTACT)) {
			// 通讯录用户(国家管理用户和省级用户组）
			AdminUserInfoBean countryUserInfoBean = userInfoBusiness.getAdminUserInfoByKey(2);
			list.add(userInfoBean2TreeNode(countryUserInfoBean));
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			condMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			List<AdminUserGroupBean> result = userGroupBusiness.getAdminUserGroupListByMap(condMap);
			for (AdminUserGroupBean userGroupBean : result) {
				list.add(userGroupBean2TreeNode(userGroupBean));
			}
		} else if (id != 0 && !name.contains(CUSTOM)) {
			// 查询本级通讯录用户
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			condMap.put("adminUserType", String.valueOf(DeclareAdminConfig.ADMIN_TYPE_COMMON));
			condMap.put("groupId", String.valueOf(id));
			List<AdminUserInfoBean> result = userInfoBusiness.getAdminUserInfoListByMap(condMap);
			for (AdminUserInfoBean userInfoBean : result) {
				list.add(userInfoBean2TreeNode(userInfoBean));
			}
			// 查询下级用户组
			AdminUserGroupBean groupBean = userGroupBusiness.getAdminUserGroupByKey(id);
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				condMap = new HashMap<String, String>();
				condMap.put("status", String.valueOf(Constants.STATUS_VALID));
				condMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_CITY);
				condMap.put("province", groupBean.getProvince());
				List<AdminUserGroupBean> groupResult = userGroupBusiness.getAdminUserGroupListByMap(condMap);
				for (AdminUserGroupBean userGroupBean : groupResult) {
					list.add(userGroupBean2TreeNode(userGroupBean));
				}
			} else if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
				condMap = new HashMap<String, String>();
				condMap.put("status", String.valueOf(Constants.STATUS_VALID));
				condMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_REGION);
				condMap.put("province", groupBean.getProvince());
				condMap.put("city", groupBean.getCity());
				List<AdminUserGroupBean> groupResult = userGroupBusiness.getAdminUserGroupListByMap(condMap);
				for (AdminUserGroupBean userGroupBean : groupResult) {
					list.add(userGroupBean2TreeNode(userGroupBean));
				}
			}
		} else if (id != 0 && name.contains(CUSTOM)) {
			// 自定义组用户
			List<MessageGroupUserBean> result = groupUserBusiness.getMessageGroupUserByGroupId(id);
			for (MessageGroupUserBean groupUserBean : result) {
				AdminUserInfoBean userInfoBean = userInfoBusiness.getAdminUserInfoByKey(groupUserBean.getUserId());
				list.add(userInfoBean2TreeNode(userInfoBean));
			}
		}
		HttpResponseKit.printJson(request, response, list, "");
	}

	class MessageGroupTreeNode {
		private int id = 0;
		private String name = "";
		private String isParent = "";

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIsParent() {
			return isParent;
		}

		public void setIsParent(String isParent) {
			this.isParent = isParent;
		}
	}

	private MessageGroupTreeNode userGroupBean2TreeNode(AdminUserGroupBean bean) {
		MessageGroupTreeNode node = new MessageGroupTreeNode();
		node.setId(bean.getGroupId());
		node.setName(bean.getGroupName());
		node.setIsParent(String.valueOf(true));
		return node;

	}

	private MessageGroupTreeNode userInfoBean2TreeNode(AdminUserInfoBean bean) {
		MessageGroupTreeNode node = new MessageGroupTreeNode();
		node.setId(bean.getUserId());
		node.setName(bean.getAdminUserName());
		node.setIsParent(String.valueOf(false));
		return node;

	}

	private MessageGroupTreeNode groupBean2TreeNode(MessageGroupBean bean) {
		MessageGroupTreeNode node = new MessageGroupTreeNode();
		node.setId(bean.getGroupId());
		node.setName(CUSTOM + ": " + bean.getGroupName());
		node.setIsParent(String.valueOf(true));
		return node;

	}

}
