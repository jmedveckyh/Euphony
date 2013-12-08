<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="artists.title">
    <s:layout-component name="body">
        <div class="back"><a href="./../artists"><f:message key="action.back.to.artists"/></a></div>
        
        <h3><c:out value="${actionBean.album.title}"/></h3>

        <c:set var="numberOfSongs" scope="session" value="${actionBean.songsInArtist}"/>
        <c:choose>
            <c:when test="${empty numberOfSongs}">
                <br><i><f:message key="playlist.songs.none"/></i>
            </c:when>
            <c:otherwise>
                <h4><f:message key="playlist.listofsongs"/></h4>

                <table class="basic">
                    <tr>
                        <th><f:message key="song.title"/></th>
                        <th><f:message key="song.artist"/></th>
                        <th><f:message key="song.album"/></th>
                    </tr>
                    <c:forEach items="${actionBean.songsInArtist}" var="song">
                        <tr>
                            <td class="blackTd">
                                <s:link beanclass="com.musiclibrary.euphonyweb.SongActionBean" event="details">
                                    <s:param name="song.id" value="${song.id}"/>
                                    <c:out value="${song.title}"/>
                                </s:link></td>
                            <td class="silverTd"><c:out value="${song.artist.name}"/></td>
                            <td class="silverTd"><c:out value="${song.album.title}"/></td>
                        </tr>   
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>


    </s:layout-component>
</s:layout-render>
