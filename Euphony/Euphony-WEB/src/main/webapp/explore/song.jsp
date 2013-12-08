<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="song.detail.title">
    <s:layout-component name="body">
        <div class="back"><a href="./../songs"><f:message key="action.back.to.songs"/></a></div>

        <h3><c:out value="${actionBean.song.title}"/></h3>

        <table class="showSongDetail">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${not empty actionBean.song.album.cover}">
                            <img src="${pageContext.request.contextPath}/upload/${actionBean.song.album.cover}"/>
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/upload/nocover.png"/><br>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <f:message key="song.by"/> <strong>
                        <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showArtist">
                            <s:param name="mainId" value="${actionBean.song.artist.id}"/>
                            <c:out value="${actionBean.song.artist.name}"/>
                        </s:link></strong>
                    <br>
                    <f:message key="albums.album"/>: <strong>
                        <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showAlbum">
                            <s:param name="mainId" value="${actionBean.song.album.id}"/>
                            <c:out value="${actionBean.song.album.title}"/>
                        </s:link></strong>
                    <br><br>
                    <i><c:out value="${actionBean.song.genre.name}"/></i>
                    <br><br>
                    <f:message key="song.bitrate"/>: 
                    <strong><c:out value="${actionBean.song.bitrate}"/></strong>
                    <br>
                    <f:message key="song.trackNumber"/>: 
                    <strong><c:out value="${actionBean.song.trackNumber}"/></strong>
                    <br> 
                    <i><c:out value="${actionBean.song.comment}"/></i>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                </td>
            </tr>  
        </table>

    </s:layout-component>
</s:layout-render>
