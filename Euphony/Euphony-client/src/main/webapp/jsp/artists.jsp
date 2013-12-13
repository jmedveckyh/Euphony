<%-- 
    Document   : artists
    Created on : Dec 13, 2013, 4:12:08 PM
    Author     : Medo
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:layout-render name="./../layout.jsp" titlekey="menu.artists">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" var="actionBean"/>
            <h2><fmt:message key="menu.artists"/></h2>
                <c:choose>
                    <c:when test="${fn:length(actionBean.artists) == 0}">
                        <i><fmt:message key="menu.noartists"/></i><br/><br/>
                    </c:when>
                <c:otherwise>
                    <table class="basic">
                        <tr>
                            <th><fmt:message key="admin.artistEdit"/></th>
                            <th><fmt:message key="admin.artistDelete"/></th>
                            <th><fmt:message key="admin.artistName"/></th>
                        </tr>
                        <c:forEach items="${actionBean.artists}" var="artist">
                            <tr>
                                <td class="actionTd">
                                    <s:link beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" event="edit">
                                        <s:param name="artist.id" value="${artist.id}"/>
                                        <img src="${pageContext.request.contextPath}/img/edit.png" width="25px"/>
                                    </s:link>
                                </td>
                                <td class="actionTd">
                                    <s:link beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" event="delete">
                                        <s:param name="artist.id" value="${artist.id}"/>
                                        <img src="${pageContext.request.contextPath}/img/delete.png" width="25px"/>
                                    </s:link>
                                </td>
                                <td><c:out value="${artist.name}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <s:form beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" name="requiredForm" onsubmit="return validateArtistForm()">
                <fieldset><legend><fmt:message key="admin.newArtist"/></legend>
                    <%@include file="artistform.jsp"%>
                    <s:submit name="add"><fmt:message key="admin.createNewArtist"/></s:submit>
                </fieldset>
            </s:form>
    </s:layout-component>
</s:layout-render>
