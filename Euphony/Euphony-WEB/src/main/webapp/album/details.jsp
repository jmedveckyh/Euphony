<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:useActionBean beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" var="details"/>
<s:layout-render name="/layout.jsp" titlekey="${details.album.title}">
    <s:layout-component name="body">
        <h1>${details.album.title}</h1>
        <img src="${pageContext.request.contextPath}/upload/<c:out value="${details.album.cover}"/>"/>
        <p><f:message key="album.comment"/>: ${details.album.comment}</p>
        <p><f:message key="album.releaseDate"/>: ${details.album.releaseDate}</p>
        <fieldset><legend>SONGY</legend>
            <table class="basic">
            <tr>                
                <th><f:message key="song.trackNumber"/></th>
                <th><f:message key="song.title"/></th>
            </tr>
                <c:forEach items="${actionBean.album.songs}" var="song">
                <tr>
                <td><c:out value="${song.trackNumber}"/></td>
                <td><c:out value="${song.title}"/></td>
                </tr>
                </c:forEach>
            </table>
        </fieldset>
        <fieldset><legend>GENRES</legend>
            <table class="basic">
            <tr>                
                <th><f:message key="genre.name"/></th>
            </tr>
                <c:forEach items="${actionBean.genres}" var="genre">
                <tr>
                <td><c:out value="${genre.name}"/></td>
                </tr>
                </c:forEach>
            </table>
        </fieldset>
        <fieldset><legend>ARTISTS</legend>
            <table class="basic">
            <tr>                
                <th><f:message key="artist.name"/></th>
            </tr>
                <c:forEach items="${actionBean.artists}" var="artist">
                <tr>
                <td><c:out value="${artist.name}"/></td>
                </tr>
                </c:forEach>
            </table>
        </fieldset>
    </s:layout-component>
</s:layout-render>
