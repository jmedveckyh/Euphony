<%-- 
    Document   : songsIncl
    Created on : 9.12.2013, 1:33:56
    Author     : Branislav Novotny <br.novotny@gmail.com> #396152
--%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
    <c:when test="${fn:length(actionBean.songs) == 0}">
        <br><i><f:message key="search.songs.none"/></i>
    </c:when>
    <c:otherwise>
        <table class="basic">
            <tr>
                <th></th>
                <th><f:message key="song.title"/></th>
                <th><f:message key="song.artist"/></th>
                <th><f:message key="song.album"/></th>
            </tr>
            <s:form beanclass="com.musiclibrary.euphonyweb.SearchActionBean">
                <c:forEach items="${actionBean.songs}" var="song">
                    
                    <tr>
                        <td><s:checkbox name="selectedSongs" value="${song.id}"/></td>
                        <td class="blackTd">
                            <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showSong">
                                <s:param name="mainId" value="${song.id}"/>
                                <c:out value="${song.title}"/>
                            </s:link>
                        </td>
                        <td class="silverTd">
                            <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showArtist">
                                <s:param name="mainId" value="${song.artist.id}"/>
                                <c:out value="${song.artist.name}"/>
                            </s:link>
                        </td>
                        <td class="silverTd">
                            <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showAlbum">
                                <s:param name="mainId" value="${song.album.id}"/>
                                <c:out value="${song.album.title}"/>
                            </s:link>
                        </td>
                    </tr>   
                </c:forEach>
            </table>
            <div class="dropdown">
                <s:select name="selectedPlaylist">
                    <s:option value=""/>
                    <s:options-collection collection="${actionBean.playlists}" label="name" value="id"/>
                </s:select>
                <s:submit name="song2playlist"><f:message key="add.song.to.playlist"/></s:submit>
            </s:form>
        </div>
        <div class="cl"></div>
    </c:otherwise>
</c:choose>