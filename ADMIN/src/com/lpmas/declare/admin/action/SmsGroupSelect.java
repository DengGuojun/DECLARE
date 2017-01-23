package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.SmsGroupBean;
import com.lpmas.declare.admin.bean.SmsGroupUserBean;
import com.lpmas.declare.admin.bean.SmsUserBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.SmsGroupBusiness;
import com.lpmas.declare.admin.business.SmsGroupUserBusiness;
import com.lpmas.declare.admin.business.SmsUserBusiness;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class SmsGroupSelect
 */
@WebServlet("/declare/admin/SmsGroupSelect.do")
public class SmsGroupSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsGroupSelect() {
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
		SmsGroupBusiness groupBusiness = new SmsGroupBusiness();
		SmsGroupUserBusiness groupUserBusiness = new SmsGroupUserBusiness();
		SmsUserBusiness userInfoBusiness = new SmsUserBusiness();

		int id = ParamKit.getIntParameter(request, "id", 0);

		List<SmsGroupTreeNode> list = new ArrayList<SmsGroupTreeNode>();
		if (id == 0) {
			// 首次加载的根节点
			List<SmsGroupBean> result = groupBusiness.getSmsGroupByOwnerId(adminHelper.getAdminUserId());
			for (SmsGroupBean groupBean : result) {
				list.add(groupBean2TreeNode(groupBean));
			}
		} else {
			// 自定义组用户
			List<SmsGroupUserBean> result = groupUserBusiness.getSmsGroupUserByGroupId(id);
			for (SmsGroupUserBean groupUserBean : result) {
				SmsUserBean smsUserBean = userInfoBusiness.getSmsUserByKey(groupUserBean.getSmsUserId());
				list.add(smsUserBean2TreeNode(smsUserBean));
			}
		}
		HttpResponseKit.printJson(request, response, list, "");
	}

	class SmsGroupTreeNode {
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


	private SmsGroupTreeNode smsUserBean2TreeNode(SmsUserBean bean) {
		SmsGroupTreeNode node = new SmsGroupTreeNode();
		node.setId(bean.getSmsUserId());
		node.setName(bean.getSmsUserName());
		node.setIsParent(String.valueOf(false));
		return node;

	}

	private SmsGroupTreeNode groupBean2TreeNode(SmsGroupBean bean) {
		SmsGroupTreeNode node = new SmsGroupTreeNode();
		node.setId(bean.getGroupId());
		node.setName(bean.getGroupName());
		node.setIsParent(String.valueOf(true));
		return node;

	}

}
