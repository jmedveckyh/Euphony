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

<fmt:bundle basename="StripesResources" /> 
<fmt:message key='menu.genres' var="title"/>
<s:layout-render name="layout.jsp" title="${title}">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" var="actionBean"/>
        <center>
            <c:choose>
                <c:when test="${fn:length(actionBean.genres) == 0}">
                    <div><font style="color: red;" ><fmt:message key="menu.nogenres"/></font></div><br /><br />
                </c:when>
                <c:otherwise>
                    <table class="zakladni">
                        <tr>
                            <th><fmt:message key="admin.genreName"/></th>
                            <th><fmt:message key="admin.genreEdit"/></th>
                            <th><fmt:message key="admin.genreDelete"/></th>
                        </tr>
                        <c:forEach items="${actionBean.genres}" var="genre">
                            <tr>
                                <td><c:out value="${genre.name}"/></td>
                                <td><s:link beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" event="edit"><s:param name="genre.id" value="${genre.id}"/><fmt:message key="admin.genreEdit"/></s:link> </td>
                                <td><s:link beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" event="delete"><s:param name="genre.id" value="${genre.id}"/><fmt:message key="admin.genreDelete"/></s:link> </td>
                                </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <s:form style="width:400px" beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean">
                <br /><fieldset><legend><fmt:message key="admin.newGenre"/></legend>
                    <%@include file="genreform.jsp"%>
                    <s:submit name="add"><fmt:message key="admin.createNewGenre"/></s:submit>
                    </fieldset>
            </s:form>
        </center>
    </s:layout-component>
</s:layout-render>
