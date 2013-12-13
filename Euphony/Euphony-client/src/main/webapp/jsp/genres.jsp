<%-- 
    Document   : genres
    Created on : Dec 13, 2013, 4:12:08 PM
    Author     : Medo
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:layout-render name="./../layout.jsp" titlekey="menu.genres">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" var="actionBean"/>
         <h2><fmt:message key="menu.genres"/></h2>  
            <c:choose>
                <c:when test="${fn:length(actionBean.genres) == 0}">
                    <i><fmt:message key="menu.nogenres"/></i><br/><br/>
                </c:when>
                <c:otherwise>
                    <table class="basic">
                        <tr>
                            <th><fmt:message key="admin.genreEdit"/></th>
                            <th><fmt:message key="admin.genreDelete"/></th>
                            <th><fmt:message key="admin.genreName"/></th>
                        </tr>
                        <c:forEach items="${actionBean.genres}" var="genre">
                        <tr>
                            <td class="actionTd">
                                <s:link beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" event="edit">
                                    <s:param name="genre.id" value="${genre.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/edit.png" width="25px"/>
                                </s:link>
                            </td>
                            <td class="actionTd">
                                <s:link beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" event="delete">
                                    <s:param name="genre.id" value="${genre.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/delete.png" width="25px"/>
                                </s:link>
                            </td>
                            <td><c:out value="${genre.name}"/></td>
                        </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <s:form beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" name="requiredForm" onsubmit="return validateGenreForm()">
                <br/>
                <fieldset><legend><fmt:message key="admin.newGenre"/></legend>
                    <%@include file="genreform.jsp"%>
                    <s:submit name="add"><fmt:message key="admin.createNewGenre"/></s:submit>
                </fieldset>
            </s:form>
    </s:layout-component>
</s:layout-render>
