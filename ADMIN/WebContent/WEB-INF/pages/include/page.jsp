<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form name="formPage" method="post" action="">
<input type="hidden" name="pageNum" id="pageNum" value=""/>
<%for(String[] condArray :COND_LIST){ %>
<input type="hidden" name="<%=condArray[0] %>" id="<%=condArray[0] %>" value="<%=condArray[1] %>"/><%} %>
</form>
<div class="footer_num" style="width: 97%;">
  <div class="item">
       共<em><%=PAGE_BEAN.getTotalPageNumber() %></em>页&nbsp;&nbsp;
      每页<em><%=PAGE_BEAN.getPageSize() %></em>条&nbsp;&nbsp;
      当前是第<em><%=PAGE_BEAN.getCurrentPageNumber() %></em>页
  </div>
  <div class="item">
       <a onclick="javascript:goPage('formPage','1');">首页</a>
       <%if(PAGE_BEAN.getCurrentPageNumber() >1){ %><a onclick="javascript:goPage('formPage',<%=PAGE_BEAN.getPrePageNumber() %>);">上一页</a><%}%>
       <%if(PAGE_BEAN.getCurrentPageNumber()< PAGE_BEAN.getTotalPageNumber()){ %><a onclick="javascript:goPage('formPage',<%=PAGE_BEAN.getNextPageNumber() %>);">下一页</a><%}%>
       <a onclick="javascript:goPage('formPage',<%=PAGE_BEAN.getTotalPageNumber() %>);">末页</a>
       <span>跳转&nbsp;<input type="text" name="goPageNum" id="goPageNum" class="input_xs" onkeyup="value=value.replace(/[^\d]/g,'')">&nbsp;页</span>
       <a onclick="javascript:goInputPage('formPage',<%=PAGE_BEAN.getTotalPageNumber() %>);"><img src="<%=STATIC_URL%>/images/go.gif"></a>
   </div>
</div>