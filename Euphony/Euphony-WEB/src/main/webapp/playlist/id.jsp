<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="playlist.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" var="actionBean"/>
        <h3><c:out value="${actionBean.playlist.name}"/></h3>

        <table border="0" class="playlistEditSubMenu">
            <tr>
                <td>
                    <a href="javascript:void(0);" onclick="javascript:showDivEdit();">
                        <img src="${pageContext.request.contextPath}/img/edit-core.png" class="imgEdit">
                    </a>    
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="javascript:showDivDelete();">
                        <img src="${pageContext.request.contextPath}/img/delete-core.png" class="imgDel">
                    </a>     
                </td>
            </tr>
            <tr>
        </table>
        <s:form beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean">
            <div id="quickAddPlaylistEdit">
                <s:hidden name="playlist.id" value="${actionBean.playlist.id}"/>
                <s:text name="playlist.name" value="${actionBean.playlist.name}" class="quickAddPlaylist"/>
                <s:submit class="quickAddPlaylistSubmit" name="save">
                    <f:message key="playlist.edit"/>
                </s:submit>
            </div>
        </s:form>
        <s:form beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean">
            <div id="playlistDelete">
                <s:hidden name="playlist.id" value="${actionBean.playlist.id}"/>
                <f:message key="are.you.sure"/>
                <s:submit class="quickAddPlaylistSubmit" name="delete">
                    <f:message key="action.yes"/>
                </s:submit>
            </div>
        </s:form>
        <div class="cl"><br><br></div>



        <c:set var="numberOfSongs" scope="session" value="${actionBean.playlist.songs}"/>
        <c:choose>
            <c:when test="${empty numberOfSongs}">
                <i><f:message key="playlist.songs.none"/></i>
            </c:when>
            <c:otherwise>
                <h4><f:message key="playlist.listofsongs"/></h4>
            </c:otherwise>
        </c:choose>
        <table>
            <c:forEach items="${actionBean.playlist.songs}" var="song"> 
                <tr>
                    <td><c:out value="${song.id}"/></td>
                    <td><c:out value="${song.title}"/></td>
                    <td><c:out value="${song.bitrate}"/></td>
                    <td><c:out value="${song.trackNumber}"/></td>
                    <td><c:out value="${song.comment}"/></td>
                    <td><c:out value="${song.genre.name}"/></td>
                    <td><c:out value="${song.artist.name}"/></td>
                    <td><c:out value="${song.album.title}"/></td>
                </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>