<%-- 
    Document   : layout
    Created on : 18-Nov-2013, 02:10:16
    Author     : Tomáš Smetanka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:useActionBean beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" var="playlistActionBean"/>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"" />
            <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
            <s:layout-component name="header"/>
        </head>
        <body>
            <div class="navigation">
                <div class="logo"><h1>Euphony</h1></div>
                <div class="cl"></div>
                <div class="search">
                    <table border="0">
                        <tr>
                            <td><input type="text" name="search" class="search"></td>
                            <td><input type="submit" value=""></td>
                        </tr>
                    </table>

                </div>
                <div class="menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}"><span><f:message key="menu.explore"/></span></a></li>
                        <li><s:link beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" event="add"><span><f:message key="menu.newplaylist"/></span></s:link></li>
                    </ul>
                    <h2><f:message key="menu.playlists"/></h2>
                    <hr>
                    <ul>
                        <c:forEach items="${playlistActionBean.playlists}" var="playlist">
                            <li>
                                <s:link beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" event="edit"><s:param name="playlist.id" value="${playlist.id}"/><span>${playlist.name}</span></s:link>
                            </li>
                        </c:forEach> 
                </div>
            </div>
            <div class="userPanel">
                <ul>
                    <li class="drop">
                        <a href="#" class="username">Admin</a>
                        <div class="dropdownContain">
                            <div class="dropOut">
                                <div class="triangle"></div>
                                <ul>
                                    <li><s:link beanclass="com.musiclibrary.euphonyweb.GenreActionBean"><f:message key="menu.admin.newgenre"/></s:link></li>
                                    <li><s:link beanclass="com.musiclibrary.euphonyweb.ArtistActionBean"><f:message key="menu.admin.newartist"/></s:link></li>
                                    <li><s:link beanclass="com.musiclibrary.euphonyweb.AlbumActionBean"><f:message key="menu.admin.newalbum"/></s:link></li>
                                    <li><s:link beanclass="com.musiclibrary.euphonyweb.SongActionBean"><f:message key="menu.admin.newsong"/></s:link></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="content">
                <s:messages/>
                <s:layout-component name="body"/>
            </div>
        </body>
    </html>
</s:layout-definition>
