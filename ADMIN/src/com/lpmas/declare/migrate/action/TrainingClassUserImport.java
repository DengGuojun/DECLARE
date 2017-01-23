package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.action.TrainingClassUserCommit;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassUserBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.declare.migrate.bean.PersonInfoMajor1Bean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor2Bean;
import com.lpmas.declare.migrate.business.PersonInfoMajor1Business;
import com.lpmas.declare.migrate.business.PersonInfoMajor2Business;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class TrainingClassUserImport
 */
@WebServlet("/declare/migrate/TrainingClassUserImport.do")
public class TrainingClassUserImport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserImport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = 0;
		List<String> message = new ArrayList<String>();
		ReturnMessageBean returnMessage = new ReturnMessageBean();
		try {
			FileUploadKit fileUploadKit = new FileUploadKit();
			String fileName = UuidKit.getUuid();
			String filePath = DateKit.getCurrentDateTime("yyyy" + "_" + "MM" + "_" + "dd");
			String uploadPath = FileInfoConfig.ADMIN_FILE_PATH + filePath;
			fileUploadKit.setAllowedFileType(FileInfoConfig.ALLOWED_FILE_TYPE);// 设置允许上传的文件类型
			fileUploadKit.setMaxSize(FileInfoConfig.MAX_SIZE);
			FileUploadResultBean resultBean = fileUploadKit.fileUpload(request, "file", uploadPath, fileName);
			String extensionFileName = null;
			// 获取上传结果
			if (resultBean.getResult()) {
				List<FileUploadItem> list = resultBean.getFileItemList();
				for (FileUploadItem item : list) {
					if (item.getResult()) {
						extensionFileName = item.getExtensionFileName();
					} else {
						returnMessage.setMessage(item.getResultContent());
						message.add(returnMessage.getMessage());
					}
				}
			} else {
				returnMessage.setMessage(resultBean.getResultContent());
				message.add(returnMessage.getMessage());
			}
			// 读取excel表
			ExcelReadKit excelReadKit = new ExcelReadKit();
			ExcelReadResultBean excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0);
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
				PersonInfoMajor1Business personInfoMajor1Business = new PersonInfoMajor1Business();
				PersonInfoMajor2Business personInfoMajor2Business = new PersonInfoMajor2Business();
				TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
				DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
				DeclareInfoBean declareInfoBean = null;
				TrainingClassInfoBean trainingClassInfoBean = null;
				PersonInfoMajor2Bean personInfoMajor2Bean = null;
				PersonInfoMajor1Bean personInfoMajor1Bean = null;
				TrainingClassUserBean trainingClassUserBean = null;
				String userId = "";

				for (List<String> content : contentList) {
					try {
						if (!content.get(0).trim().isEmpty()) {
							
							trainingClassUserBean = trainingClassUserBusiness.getTrainingClassUserByMemo(content.get(0).trim());
							if (trainingClassUserBean != null) {
								continue;
							}
							
							userId = "";
							// 获取班级信息
							trainingClassInfoBean = trainingClassInfoBusiness
									.getTrainingClassInfoByMemo(content.get(1).trim());
							if (trainingClassInfoBean == null) {
								message.add(content.get(0) + "：找不到班级");
								continue;
							}

							personInfoMajor1Bean = personInfoMajor1Business
									.getPersonInfoMajor1ByKey(content.get(2).trim());
							personInfoMajor2Bean = personInfoMajor2Business
									.getPersonInfoMajor2ByKey(content.get(2).trim());
							int studentType = Double.valueOf(content.get(3).trim()).intValue();
							if (studentType == 1 || studentType == 2 || studentType == 3) {
								if (personInfoMajor1Bean != null)
									userId = personInfoMajor1Bean.getUserId();
								else {
									message.add(content.get(0) + "：详细资料找不到");
									continue;
								}
							} else if (studentType == 4 || studentType == 5) {
								if (personInfoMajor2Bean != null)
									userId = personInfoMajor2Bean.getUserId();
								else {
									message.add(content.get(0) + "：详细资料找不到");
									continue;
								}
							} else {
								message.add(content.get(0) + "：STUDENT TYPE错误");
								continue;
							}

							declareInfoBean = declareInfoBusiness.getDeclareInfoByMemo(userId);
							if (declareInfoBean == null) {
								message.add(content.get(0) + "：申报资料找不到");
								continue;
							}
							
							//检查学员认定
							if(StringKit.isValid(content.get(4).trim())){
								Integer isChecked = Double.valueOf(content.get(4).trim()).intValue();
								if(isChecked==Constants.STATUS_VALID){
									declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_APPROVE);
									declareInfoBean.setModifyTime(DateKit.getCurrentTimestamp());
									declareInfoBean.setAuthOrganizationId(trainingClassInfoBean.getOrganizationId());
									declareInfoBean.setModifyUser(new AdminUserHelper(request).getAdminUserId());
									if(declareInfoBusiness.updateDeclareInfo(declareInfoBean)<=0){
										message.add(content.get(0) + "：学员认定更新失败");
										continue;
									}
								}
							}

							Timestamp createTime = new Timestamp(
									DateKit.str2Date(content.get(7).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());

							trainingClassUserBean = new TrainingClassUserBean();
							trainingClassUserBean.setClassId(trainingClassInfoBean.getClassId());
							trainingClassUserBean.setDeclareId(declareInfoBean.getDeclareId());
							trainingClassUserBean.setExamResult(0);
							trainingClassUserBean.setHasCertification(0);
							if (content.size()>=10&&StringKit.isValid(content.get(9).trim())) {
								trainingClassUserBean.setExamResult(Double.valueOf(content.get(9).trim()).intValue()>0 ? 1:0 );
							}
							if (content.size()>=11&&StringKit.isValid(content.get(10).trim())) {
								trainingClassUserBean.setHasCertification(Double.valueOf(content.get(10).trim()).intValue()>0 ? 1:0);
							}
							trainingClassUserBean.setUserStatus(TrainingClassUserConfig.USER_STATUS_APPROVE);
							trainingClassUserBean.setStatus(Constants.STATUS_VALID);
							trainingClassUserBean.setCreateTime(createTime);
							trainingClassUserBean.setSignUpTime(createTime);
							trainingClassUserBean.setMemo(content.get(0).trim());
							result = trainingClassUserBusiness.addTrainingClassUserWithCreateTime(trainingClassUserBean);
							if (result <= 0) {
								message.add(content.get(0) + "：数据插入失败");
								continue;
							}
						}
					} catch (Exception e) {
						message.add(content.get(0) + "：数据插入失败:" + e.getMessage());
						continue;
					}
				}
			}
		} catch (Exception e) {
			HttpResponseKit.alertMessage(response, "导入失败：" + e.getMessage(), HttpResponseKit.ACTION_NONE);
			return;
		}

		HttpResponseKit.printJson(request, response, message, "");
		return;
	}
}
