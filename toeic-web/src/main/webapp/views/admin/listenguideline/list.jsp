<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Sac Xuan
  Date: 4/25/2019
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin-guideline-listen-list.html"/>
<c:url value="/admin-guideline-listen-edit.html" var="listenguidelineEditUrl">
    <c:param name="urlType" value="url_edit"></c:param>
</c:url>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
    <style>
        .error{
            color: red;
        }
    </style>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <a href="${listenguidelineEditUrl}" type="button">Thêm Bài Hướng Dẫn</a>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>


                    <div class="table-responsive">
                        <ftm:bundle basename="ResourcesBundle" >
                            <display:table id="tableList" name="items.listResult" partialList="true" pagesize="${items.maxPageItems}"
                            size="${items.totalItems}" sort="external" class="table table-fcv-ace table-striped table-birdered table-hover dataTable no-footer "
                            style="margin: 30em 1.5em; " requestURI="${formUrl}">
                                <display:column property="title"  titleKey="label.guideline.listen.title"></display:column>
                                <display:column property="content" titleKey="label.guideline.listen.content"></display:column>
                            </display:table>
                        </ftm:bundle>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
