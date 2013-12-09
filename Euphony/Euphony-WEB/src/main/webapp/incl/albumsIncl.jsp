<%-- 
    Document   : albumsIncl
    Created on : 9.12.2013, 1:40:08
    Author     : Branislav Novotny <br.novotny@gmail.com> #396152
--%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
    <c:when test="${fn:length(actionBean.albums) == 0}">
        <br><i><f:message key="search.albums.none"/></i>
    </c:when>
    <c:otherwise>
        <table class="basicSquare">
            <c:set var="counter" value="0"/>
            <tr>
                <c:forEach items="${actionBean.albums}" var="album">
                    <c:set var="counter" value="${counter + 1}"/>
                    <td>
                        <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showAlbum">
                            <c:choose>
                                <c:when test="${not empty album.cover}">
                                    <img src="${pageContext.request.contextPath}/upload/${album.cover}"/><br>
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/upload/nocover.png"/><br>
                                </c:otherwise>
                            </c:choose>
                            <div class="blackTd">
                                <s:param name="mainId" value="${album.id}"/>
                                <c:out value="${album.title}"/>
                            </s:link>
                        </div><br>
                        <div class="silverTd"><f:message key="album.releasedate"/>: <c:out value="${album.releaseDate.dayOfMonth}.${album.releaseDate.monthOfYear}.${album.releaseDate.year}"/></div><br>
                    </td>
                    <c:choose>
                        <c:when test="${counter % 4 == 0}">
                        </tr><tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
    </c:otherwise>
</c:choose>