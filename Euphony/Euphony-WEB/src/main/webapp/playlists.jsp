<%-- 
    Document   : playlists
    Created on : 23-Nov-2013, 04:18:11
    Author     : Tomas Smetanka
--%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:useActionBean beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" var="playlistActionBean"/>
<c:choose>
    <c:when test='${not empty "${playlistActionBean.playlists}"}'>
        <h2><f:message key="menu.playlists"/></h2>
    </c:when>
</c:choose>
<hr> 
<ul>
    <c:forEach items="${playlistActionBean.playlists}" var="playlist">
        <li>
            <s:link beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" event="edit"><s:param name="playlist.id" value="${playlist.id}"/><span>${playlist.name}</span></s:link>
            </li>
    </c:forEach> 
</ul>
