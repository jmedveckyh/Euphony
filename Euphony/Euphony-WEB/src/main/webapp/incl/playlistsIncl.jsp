<%-- 
    Document   : playlistsIncl
    Created on : 9.12.2013, 3:40:28
    Author     : Branislav Novotny <br.novotny@gmail.com> #396152
--%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
    <c:when test="${fn:length(actionBean.playlists) == 0}">
        <br><i><f:message key="search.playlists.none"/></i>
    </c:when>
    <c:otherwise>
        <table class="basic">
            <tr>
                <th><f:message key="playlist.name"/></th>
            </tr>
            <c:forEach items="${actionBean.playlists}" var="playlist">
                <tr>
                    <td>
                <s:link beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" event="show">
                    <f:parseNumber var="i" type="number" value="${playlist.id}"/>
                    <s:param name="playlist.id" value="${i}"/><c:out value="${playlist.name}"/>
                </s:link>
                </td>
                </tr>
            </c:forEach> 
        </table>
    </c:otherwise>
</c:choose>