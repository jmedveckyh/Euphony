<%-- 
    Document   : layout
    Created on : Dec 13, 2013, 4:25:34 PM
    Author     : Medo
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Locale" %>
<s:layout-definition>
    <html>
        <head>
            <title><c:out value="${title}" /></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
            <s:layout-component name="hlavicka"/>
        </head>
        <body>
            <fmt:bundle basename="StripesResources">
                <div id="layout">

                    <div id="navigace">
                        <ul>
                            <li><s:link href="/jsp/genres.jsp"><fmt:message key="menu.genres"/></s:link></li>
                            <li><s:link href="/jsp/artists.jsp"> <fmt:message key="menu.artists"/></s:link></li>
                            </ul>
                        </div>
                        <h1><c:out value="${title}" /></h1>
                    <div id="obsah">
                        <s:layout-component name="telo"/>
                    </div>
                </div>
            </fmt:bundle>
        </body>
    </html>
</s:layout-definition>
