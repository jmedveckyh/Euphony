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
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
            <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/js/global.js" type="text/javascript"></script>
            <s:layout-component name="header"/>
        </head>
        <body>
            <div class="home" onclick="window.location.href = '${pageContext.request.contextPath}'"></div>
            <div class="navigation">
                <div class="logo"><h1>Euphony</h1></div>
                <div class="cl"></div>
                <div class="search">
                    <table border="0">
                        <s:form beanclass="com.musiclibrary.euphonyweb.SearchActionBean">
                            <tr>
                                <td><s:text name="phrase" class="search"/></td>
                                <td><s:submit name="search" value=""></s:submit></td>
                                </tr>
                        </s:form>
                    </table>
                </div>
                <div class="menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}"><span><f:message key="menu.explore"/></span></a></li>
                        <li><a href="javascript:void(0);" onclick="javascript:showDiv();"><span><f:message key="menu.newplaylist"/></span></a></li>
                                    <f:message key='playlist.name' var="name"/>
                                    <s:form beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" name="quickAddPlaylist" onsubmit="return validateAddPlaylistForm()">
                            <li id="quickAddPlaylist">
                                <s:text class="quickAddPlaylist" name="playlist.name" id="pn"/>
                                <s:submit name="add" class="quickAddPlaylistSubmit">
                                    <f:message key="menu.newplaylist.add"/>
                                </s:submit>
                            </li>
                        </s:form>
                    </ul>
                    <%@include file="playlists.jsp"%>
                    <hr>
                </div>
            </div>
            <div class="userPanel">
                <ul>
                    <li><s:link beanclass="com.musiclibrary.euphonyweb.AuthActionBean" event="logout"><f:message key="menu.logout"/></s:link></li>
                    <c:if test="${pageContext.session.getAttribute('admin')==true}">
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
                    </c:if>
                    </ul>
                </div>
                <div class="content">
                <s:messages/>
                <s:layout-component name="body"/>
                <hr>
            </div>
        </body>
    </html>
</s:layout-definition>