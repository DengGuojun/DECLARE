package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.NavigationConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingMaterialConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class NavigationList
 */
@WebServlet("/declare/admin/NavigationList.do")
public class NavigationList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationList() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request,response);

		String navigationType = ParamKit.getParameter(request, "navigationType", ""); // 获取点击的哪个标签栏
	
		if (NavigationConfig.SYSTEM_SETTING.equals(navigationType)) {
			// 点击了系统设置按钮
			if (adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.SEARCH)) {
				// 是否有 人员管理的权限
				 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/AdminUserGroupList.do");
				rd.forward(request, response) ;
			} else if (adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_TYPE, OperationConfig.SEARCH)) {
				// 是否有专业类型的权限
				 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/MajorTypeList.do");
				rd.forward(request, response);
			} else if (adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_INFO, OperationConfig.SEARCH)) {
				// 是否有专业类型的权限
				 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/MajorInfoList.do");
				rd.forward(request, response);
			} else if (adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_ROLE, OperationConfig.SEARCH)) {
				// 判断是否有角色管理的权限
				 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/AdminRoleInfoList.do");
				rd.forward(request, response);
			} else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG,
					OperationConfig.SEARCH)) {
				 
				// 判断是否有学院录入的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingClassUserConfigList.do");
				rd.forward(request, response);
			} else {
				 
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限", HttpResponseKit.ACTION_HISTORY_BACK);
				return ;
			}
		}else if(NavigationConfig.PROJECT_MANAGEMENT.equals(navigationType)){
			//点击了项目管理按钮
			if (adminUserHelper.hasPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO, OperationConfig.SEARCH)) {
				// 是否有主管部门的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/GovernmentOrganizationInfoList.do");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_TARGET, OperationConfig.SEARCH)) {
				//培训任务数的权限 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingClassTargetList.do");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CUSTOM_COUNT, OperationConfig.SEARCH)) {
				//自定义统计的权限 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingClassUserSearch.do");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_KINDS_COUNT, OperationConfig.SEARCH)) {
				//统计分析的权限 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/DeclareInfoCountList.do");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.SEARCH)) {
				//工作通知的权限 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/AnnouncementInfoList.do");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.FILE_INFO, OperationConfig.SEARCH)) {
				//资料下载的权限 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/FileInfoList.do");
				rd.forward(request, response);
			} else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		}else if(NavigationConfig.INFORMATION_BANK.equals(navigationType)){
			//判断是否点击了信息库按钮
			if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_AUTH, OperationConfig.SEARCH)) {
				// 是否有认定学员管理的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/DeclareInfoAuthList.do?trainingType=1");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO, OperationConfig.SEARCH)) {
				// 是否有认定学员管理的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingOrganizationInfoList.do?organizationType="+TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE);
				rd.forward(request, response);
			} else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return ;
			}	
		} else if(NavigationConfig.EDUCATION.equals(navigationType)){
			//判断是否点击了教育培训按钮
			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.SEARCH)) {
				// 是否有现代青年农场主培训的权限
				 
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingClassInfoList.do?trainingType=1");
				rd.forward(request, response);
				 
			}else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);	
				return  ;
			}
		}else if(NavigationConfig.TRAINING_MATERIAL.equals(navigationType)){
			//是否点击了培训教材库按钮 
			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
				// 是否有通用教材的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingMaterialInfoList.do?materialType="+TrainingMaterialConfig.MATERIAL_GENERAL);
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
				// 是否有专业技能教材的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingMaterialInfoList.do?materialType="+TrainingMaterialConfig.MATERIAL_PROFESSIONAL);
				rd.forward(request, response);
			}else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return ;
			}
		}else if(NavigationConfig.PRACTICE_ORGANIZATION.equals(navigationType)){
			//是否点击了实训基地库
			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO, OperationConfig.CREATE)) {
				// 是否有实训单位录入的的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingOrganizationInfoManage.do?organizationType="+TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING);
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO, OperationConfig.SEARCH)) {
				// 是否有实训单位录入的的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingOrganizationInfoList.do?organizationType="+TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING);
				rd.forward(request, response);
			}else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		}else if(NavigationConfig.TEACHER_INFO.equals(navigationType)){
			//判断是否点击了培育师资库
			if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.SEARCH)) {
				// 是否有本级师资的的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TeacherInfoList.do?teacherRange=2");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_POPEDOM, OperationConfig.SEARCH)) {
				// 是否有本辖区师资的的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TeacherInfoList.do?teacherRange=1");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_ALL, OperationConfig.SEARCH)) {
				// 是否有入库师资的的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TeacherInfoList.do?teacherRange=0");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_STATISTICS, OperationConfig.SEARCH)) {
				// 是否有入库师资师资的的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TeacherStatisticsList.do");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_STATISTICS_BY_LEVEL, OperationConfig.SEARCH)) {
				// 是否有入库师资师资的的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TeacherStatisticsByLevelList.do");
				rd.forward(request, response);
			}else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		}else if(NavigationConfig.TRAINING_ORGANIZATION.equals(navigationType)){
			//判断是否点击了机构单位库
			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO, OperationConfig.CREATE)) {
				// 是否有培训单位库的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingOrganizationInfoManage.do?organizationType="+TrainingOrganizationConfig.ORGANIZATION_TRAINING);
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO, OperationConfig.SEARCH)) {
				// 是否有培训单位库的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/TrainingOrganizationInfoList.do?organizationType="+TrainingOrganizationConfig.ORGANIZATION_TRAINING);
				rd.forward(request, response);
			}else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		}else if(NavigationConfig.TRAINING_OBJECT.equals(navigationType)){
			//判断是否点击了培育对象库
			if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.SEARCH)) {
				// 是否有对象推荐的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/DeclareInfoRecommendList.do");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.SEARCH)) {
				// 是否有对象推荐的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/DeclareInfoReviewList.do?modelType=1&declareType=1");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_MANAGE, OperationConfig.SEARCH)) {
				// 是否有对象推荐的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/DeclareInfoReviewList.do?modelType=2&declareType=1");
				rd.forward(request, response);
			}else if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_CLASSIFY, OperationConfig.SEARCH)) {
				// 是否有对象推荐的权限
				RequestDispatcher rd = request.getRequestDispatcher("/declare/admin/DeclareInfoReviewList.do?modelType=3");
				rd.forward(request, response);
			}else {
				HttpResponseKit.alertMessage(response, "您没有这模块的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
