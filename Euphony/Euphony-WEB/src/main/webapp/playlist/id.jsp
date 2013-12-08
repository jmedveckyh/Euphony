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

        <c:set var="numberOfSongs" scope="session" value="${actionBean.playlist.songs}"/>
        <c:choose>
            <c:when test="${empty numberOfSongs}">
                <br><i><f:message key="playlist.songs.none"/></i>
            </c:when>
            <c:otherwise>
                <h4><f:message key="playlist.listofsongs"/></h4>

                <table class="basic">
                    <tr>
                        <th></th>
                        <th><f:message key="song.title"/></th>
                        <th><f:message key="song.artist"/></th>
                        <th><f:message key="song.album"/></th>
                    </tr>
                    <s:form beanclass="com.musiclibrary.euphonyweb.Song2PlaylistActionBean">
                        <c:forEach items="${actionBean.playlist.songs}" var="song">
                            <tr>
                                <td><s:checkbox name="selectedSongs" value="${song.value.id}"/></td>
                                <td class="blackTd">
                                    <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showSong">
                                        <s:param name="mainId" value="${song.value.id}"/>
                                        <c:out value="${song.value.title}"/>
                                    </s:link>
                                </td>
                                <td class="silverTd">
                                    <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showArtist">
                                        <s:param name="mainId" value="${song.value.artist.id}"/>
                                        <c:out value="${song.value.artist.name}"/>
                                    </s:link>
                                </td>
                                <td class="silverTd">
                                    <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showAlbum">
                                        <s:param name="mainId" value="${song.value.album.id}"/>
                                        <c:out value="${song.value.album.title}"/>
                                    </s:link>
                                </td>
                            </tr>   
                        </c:forEach>
                    </table>
                    <br>
                    <div class="dropdown">
                        <s:hidden name="selectedPlaylist" value="${actionBean.playlist.id}"/>
                        <s:submit name="songFromPlaylist"><f:message key="remove.song.from.playlist"/></s:submit>
                    </s:form>
                </div>
            </c:otherwise>
        </c:choose>

    </s:layout-component>
</s:layout-render>