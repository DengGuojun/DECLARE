package com.lpmas.declare.invoker.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.invoker.bean.ArticleInfoBean;
import com.lpmas.declare.invoker.bean.ArticleResponseBean;
import com.lpmas.declare.invoker.bean.ArticleResponseBean.ContentEntity;
import com.lpmas.framework.transfer.HttpClientKit;
import com.lpmas.framework.transfer.HttpClientResultBean;
import com.lpmas.framework.util.JsonKit;

public class ArticleInfoBusiness {

	public List<ArticleInfoBean> getArticleInfoListByType(String articleType, int pageSize) {
		List<ArticleInfoBean> list = new ArrayList<ArticleInfoBean>();
		// 查询资讯信息
		HttpClientResultBean httpResult = new HttpClientResultBean();
		HttpClientKit httpClientKit = new HttpClientKit();
		httpResult = httpClientKit.getContent(
				"http://article.ngonline.cn/m/SectionInfo.action?appCode=AHL&sectionPaths=" + articleType
						+ "&pageSize=" + pageSize, new HashMap<String, String>());
		if (httpResult.getResult()) {
			ArticleResponseBean result = JsonKit.toBean(httpResult.getResultContent(), ArticleResponseBean.class);
			for (ContentEntity entity : result.getContent()) {
				list = entity.getArticleInfoList();
			}
		}

		return list;
	}
}
