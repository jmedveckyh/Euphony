<%-- 
    Document   : playlists
    Created on : 23-Nov-2013, 04:18:11
    Author     : Tomas Smetanka
--%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="numberOfPlaylists" scope="session" value="${actionBean.playlists}"/>
<c:choose>
    <c:when test="${not empty numberOfPlaylists}">
        <h2><f:message key="menu.playlists"/></h2>
        <hr> 
    </c:when>
</c:choose>
<ul>
    <c:forEach items="${actionBean.playlists}" var="playlist">
        <li>
            <s:link beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" event="show">
                <f:parseNumber var="i" type="number" value="${playlist.id}"/>
                <s:param name="playlist.id" value="${i}"/><span>${playlist.name}</span>
            </s:link>
        </li>
    </c:forEach> 
</ul>
