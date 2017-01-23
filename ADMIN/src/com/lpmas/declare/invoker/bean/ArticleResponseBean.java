package com.lpmas.declare.invoker.bean;

import java.util.List;

public class ArticleResponseBean {
	
	/**
     * sectionId : 2
     * sectionName : 农业头条
     * sectionPath : agroheadlines
     * articleInfoList : [{"articleId":4,"articleTitle":"title4","articleSubtitle":"","articleCode":"d2016030904","articleType":2,"articleTags":"tag4","pageQuantity":2,"sectionIds":[1,2,3,4],"articleUrl":"http://www.baidu.com","sourceId":1,"sourceName":"","originalUrl":"http://www.google.com","originalAuthor":"Jimin","pubDate":"2016-03-09","picture1":"","picture2":"","pictureList":[],"vedio1":null,"contentList":null,"questionList":null,"quotationList":null,"relevantList":null},{"articleId":2,"articleTitle":"title2","articleSubtitle":"","articleCode":"d2016030902","articleType":1,"articleTags":"tag2","pageQuantity":2,"sectionIds":[1,2,3],"articleUrl":"http://www.baidu.com","sourceId":1,"sourceName":"","originalUrl":"http://www.google.com","originalAuthor":"Jimin","pubDate":"2016-03-09","picture1":"","picture2":"","pictureList":[],"vedio1":null,"contentList":[{"pageNumber":1,"pageTitle":"","pageContent":"title2","pageImage":null,"pageUrl":null},{"pageNumber":2,"pageTitle":"","pageContent":"<p>为了欢迎2015年度总部新进员工，在工会主席何久春的带领下户外迎新活动。<\/p>","pageImage":null,"pageUrl":null},{"pageNumber":3,"pageTitle":"","pageContent":"","pageImage":null,"pageUrl":null}],"questionList":null,"quotationList":null,"relevantList":null},{"articleId":1,"articleTitle":"title1","articleSubtitle":"","articleCode":"d2016030901","articleType":1,"articleTags":"tag1","pageQuantity":2,"sectionIds":[1,2,4],"articleUrl":"http://www.baidu.com","sourceId":1,"sourceName":"","originalUrl":"http://www.google.com","originalAuthor":"Jimin","pubDate":"2016-03-09","picture1":"","picture2":"","pictureList":[],"vedio1":null,"contentList":[{"pageNumber":1,"pageTitle":"","pageContent":"title1","pageImage":null,"pageUrl":null},{"pageNumber":2,"pageTitle":"","pageContent":"<p>为了欢迎2015年度总部新进员工，在工会主席何久春的带领下户外迎新活动。<\/p>","pageImage":null,"pageUrl":null},{"pageNumber":3,"pageTitle":"","pageContent":"","pageImage":null,"pageUrl":null}],"questionList":null,"quotationList":null,"relevantList":null},{"articleId":3,"articleTitle":"title3","articleSubtitle":"","articleCode":"d2016030903","articleType":1,"articleTags":"tag3","pageQuantity":2,"sectionIds":[1,3,4],"articleUrl":"http://www.baidu.com","sourceId":1,"sourceName":"","originalUrl":"http://www.google.com","originalAuthor":"Jimin","pubDate":"2016-03-09","picture1":"","picture2":"","pictureList":[],"vedio1":null,"contentList":[{"pageNumber":1,"pageTitle":"","pageContent":"title3","pageImage":null,"pageUrl":null},{"pageNumber":2,"pageTitle":"","pageContent":"<p>为了欢迎2015年度总部新进员工，在工会主席何久春的带领下户外迎新<\/p>","pageImage":null,"pageUrl":null},{"pageNumber":3,"pageTitle":"","pageContent":"","pageImage":null,"pageUrl":null}],"questionList":null,"quotationList":null,"relevantList":null}]
     */

	private int code = 0;
	private String message = "";
	private String command = "";
	private List<ContentEntity> content;

    public void setContent(List<ContentEntity> content) {
        this.content = content;
    }

    public List<ContentEntity> getContent() {
        return content;
    }

    public static class ContentEntity {
        private int sectionId;
        private String sectionName;
        private String sectionPath;
        /**
         * articleId : 4
         * articleTitle : title4
         * articleSubtitle :
         * articleCode : d2016030904
         * articleType : 2
         * articleTags : tag4
         * pageQuantity : 2
         * sectionIds : [1,2,3,4]
         * articleUrl : http://www.baidu.com
         * sourceId : 1
         * sourceName :
         * originalUrl : http://www.google.com
         * originalAuthor : Jimin
         * pubDate : 2016-03-09
         * picture1 :
         * picture2 :
         * pictureList : []
         * vedio1 : null
         * contentList : null
         * questionList : null
         * quotationList : null
         * relevantList : null
         */
        private List<ArticleInfoBean> articleInfoList;

        public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public void setSectionPath(String sectionPath) {
            this.sectionPath = sectionPath;
        }

        public void setArticleInfoList(List<ArticleInfoBean> articleInfoList) {
            this.articleInfoList = articleInfoList;
        }

        public int getSectionId() {
            return sectionId;
        }

        public String getSectionName() {
            return sectionName;
        }

        public String getSectionPath() {
            return sectionPath;
        }

        public List<ArticleInfoBean> getArticleInfoList() {
            return articleInfoList;
        }
    }
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
	
}
