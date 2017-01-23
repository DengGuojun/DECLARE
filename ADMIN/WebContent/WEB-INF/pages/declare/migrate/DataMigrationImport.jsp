<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ include file="../../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据导入</title>
</head>
<body>
	<p>数据库导出操作时使用导出xls文件(加上列名)</p>
	<!-- 国俊完成 -->
	<form method="post" enctype="multipart/form-data"
		action="TrainingOrgImport.do" style="float: left; margin-right: 50px;">
		<div class="form-group">
			<div class="col-xs-2 form-label">培训单位文件(TAB_SCHOOL.xls)</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="RendingOrgImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">认定单位文件(RedingOrg.xls)，需要tab_index数据</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TeacherMajorImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">师资专业文件(TeachersMajor.xls)</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TeacherInfoImport.do" style="float: left; margin-right: 50px;">
		<div class="form-group">
			<div class="col-xs-2 form-label">师资人员文件(TeachersInfo.xls)</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TeacherImageInfoImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">教师头像图片信息（迁移路径对应表.xlsx）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="SchoolInfoImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">学校文件(SchoolInfo.xls)</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TeacherEvaluateImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">教师评价文件(TeachGrade.xls)</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>

	<form method="post" enctype="multipart/form-data"
		action="CountryTaskImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">区县培训目标文件（TAB_CountryTaskSum.xls）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="NextTaskImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">下级区县培训目标文件（TAB_PlanTaskSum.xls和TAB_NextTaskSum.xls整合在一个excel文件中）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TrainingOrgTaskImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">培训机构目标文件（TAB_TaskSum.xls）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TrainingClassUserImport.do"
		style="float: left; margin-right: 50px;">
		<div class="form-group">
			<div class="col-xs-2 form-label">培训学员(trainStudentInfo.xls)</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>


	<!-- 宜杰完成 -->
	<form method="post" enctype="multipart/form-data"
		action="TrainingMaterialImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">培训教材文件（ TrainingBook.xls ）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="MajorAllImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">专业维护文件（ TAB_MAJOR.xls ）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>

	<a href="MajorTypeChange.do">专业去重</a>

	<form method="post" enctype="multipart/form-data"
		action="GovernmentOrganizationInfoImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">主管部门信息文件（ TAB_SunInfo.xls ）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="AnnouncementInfoImport.do"
		style="float: left; margin-right: 50px;">
		<div class="form-group">
			<div class="col-xs-2 form-label">公告信息文件（将
				WebInfo，PersonInfo，UnitInfo，Login按顺序整合在excle中）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="FileInfoImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">上传文件信息（ WebFile.xls）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TrainingClassImport.do"
		style="float: left; margin-right: 50px;">
		<div class="form-group">
			<div class="col-xs-2 form-label">培训班信息（将TrainClass_NCZ，TrainClass_Operate，TrainClass_Special按顺序整合在excle中，需要先导入培训机构、认定机构和专业的信息）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="TrainingClassItemImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">培训课程信息（CourseArrange.xlsx）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" enctype="multipart/form-data"
		action="DeclareImageInfoImport.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">学员申报图片信息（迁移路径对应表.xlsx，需要先导入申报信息）</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						<input type="file" id="file" name="file" value="上传"
							class="form-control"><input type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>

	<form method="get" enctype="multipart/form-data"
		action="ImageAliMigrate.do">
		<div class="form-group">
			<div class="col-xs-2 form-label">头像图片上传</div>
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-8">
						上传路径 : <input name="uploadPath" /> excel文件名 : <input
							name="downloadName" /> 下载路径 : <input name="downloadPath" /> <input
							type="submit" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>

    <form method="post" enctype="multipart/form-data"
			action="AdminUserInfoImport.do">
			<div class="form-group">
				<div class="col-xs-2 form-label">用户名密码文件</div>
				<div class="col-xs-10">
					<div class="row">
						<div class="col-xs-8">
							<input type="file" id="file" name="file" value="上传"
								class="form-control"><input type="submit" value="提交" />
						</div>
					</div>
				</div>
			</div>
	</form>
</body>
</html>