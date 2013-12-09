<%-- 
    Document   : artistsIncl
    Created on : 9.12.2013, 1:42:46
    Author     : Branislav Novotny <br.novotny@gmail.com> #396152
--%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
    <c:when test="${fn:length(actionBean.artists) == 0}">
        <br><i><f:message key="search.artists.none"/></i>
    </c:when>
    <c:otherwise>
        <table class="basic">
            <tr>
                <th><f:message key="artist.name"/></th>
            </tr>
            <c:forEach items="${actionBean.artists}" var="artist">
                <tr>
                    <td>
                        <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showArtist">
                            <s:param name="mainId" value="${artist.id}"/>
                            <c:out value="${artist.name}"/>
                        </s:link>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>