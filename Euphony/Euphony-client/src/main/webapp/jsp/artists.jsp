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

<fmt:bundle basename="StripesResources" /> 
<fmt:message key='menu.artists' var="title"/>
<s:layout-render name="layout.jsp" title="${title}">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" var="actionBean"/>
        <center>
            <c:choose>
                <c:when test="${fn:length(actionBean.artists) == 0}">
                    <div><font style="color: red;" ><fmt:message key="menu.noartists"/></font></div><br /><br />
                </c:when>
                <c:otherwise>
                    <table class="zakladni">
                        <tr>
                            <th><fmt:message key="admin.artistName"/></th>
                            <th><fmt:message key="admin.artistEdit"/></th>
                            <th><fmt:message key="admin.artistDelete"/></th>
                        </tr>
                        <c:forEach items="${actionBean.artists}" var="artist">
                            <tr>
                                <td><c:out value="${artist.name}"/></td>
                                <td><s:link beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" event="edit"><s:param name="artist.id" value="${artist.id}"/><fmt:message key="admin.artistEdit"/></s:link> </td>
                                <td><s:link beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" event="delete"><s:param name="artist.id" value="${artist.id}"/><fmt:message key="admin.artistDelete"/></s:link> </td>
                                </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <s:form style="width:400px" beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean">
                <br /><fieldset><legend><fmt:message key="admin.newArtist"/></legend>
                    <%@include file="artistform.jsp"%>
                    <s:submit name="add"><fmt:message key="admin.createNewArtist"/></s:submit>
                    </fieldset>
            </s:form>
        </center>
    </s:layout-component>
</s:layout-render>
