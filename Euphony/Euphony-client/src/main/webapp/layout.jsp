<%-- 
    Document   : layout
    Created on : 13-Dec-2013, 20:10:16
    Author     : Tomáš Smetanka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
            <script src="${pageContext.request.contextPath}/js/global.js" type="text/javascript"></script>
            <s:layout-component name="header"/>
        </head>
        <body>
            <div class="navigation">
                <div class="logo" onclick="window.location.href = '${pageContext.request.contextPath}'"></div>
                <div class="menu">
                    <s:link href="${pageContext.request.contextPath}/jsp/genres.jsp"><span><f:message key="menu.genres"/></span></s:link>
                    <s:link href="${pageContext.request.contextPath}/jsp/artists.jsp"><span><f:message key="menu.artists"/></span></s:link>
                </div>
            </div>
                <div class="cl"></div>
                <div class="content">
                    <s:messages/>
                    <s:layout-component name="body"/>
                </div>
        </body>
    </html>
</s:layout-definition>