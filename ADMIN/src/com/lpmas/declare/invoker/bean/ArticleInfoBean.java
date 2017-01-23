package com.lpmas.declare.invoker.bean;

import java.util.List;

import com.lpmas.declare.invoker.bean.ArticleResponseBean.ContentEntity;

public class ArticleInfoBean {
	 
    private int articleId;
    private String articleTitle;
    private String articleSubtitle;
    private String articleCode;
    private int articleType;
    private String articleTags;
    private int pageQuantity;
    private String articleUrl;
    private int sourceId;
    private String sourceName;
    private String originalUrl;
    private String originalAuthor;
    private String pubDate;
    private String picture1;
    private String picture2;
    private Object vedio1;
    private List<Integer> questionList;
    private List<Integer> quotationList;
    private List<Integer> relevantList;
    private List<Integer> sectionIds;
    private List<String> pictureList;

    /**
     * pageNumber : 1
     * pageTitle :
     * pageContent : title2
     * pageImage : null
     * pageUrl : null
     */

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setArticleSubtitle(String articleSubtitle) {
        this.articleSubtitle = articleSubtitle;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public void setPageQuantity(int pageQuantity) {
        this.pageQuantity = pageQuantity;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public void setVedio1(Object vedio1) {
        this.vedio1 = vedio1;
    }

    public void setQuestionList(List<Integer> questionList) {
        this.questionList = questionList;
    }

    public void setQuotationList(List<Integer> quotationList) {
        this.quotationList = quotationList;
    }

    public void setRelevantList(List<Integer> relevantList) {
        this.relevantList = relevantList;
    }

    public void setSectionIds(List<Integer> sectionIds) {
        this.sectionIds = sectionIds;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }


    public int getArticleId() {
        return articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleSubtitle() {
        return articleSubtitle;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public int getArticleType() {
        return articleType;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public int getPageQuantity() {
        return pageQuantity;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public int getSourceId() {
        return sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getPicture1() {
        return picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public Object getVedio1() {
        return vedio1;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }

    public List<Integer> getQuotationList() {
        return quotationList;
    }

    public List<Integer> getRelevantList() {
        return relevantList;
    }

    public List<Integer> getSectionIds() {
        return sectionIds;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

}
