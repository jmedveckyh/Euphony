<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h3><f:message key="menu.explore"/></h3>
        <%@include file="menuUp.jsp"%>

        <table class="basicSquare">
            <c:set var="counter" value="0"/>
            <tr>
                <c:forEach items="${actionBean.albums}" var="album">
                    <c:set var="counter" value="${counter + 1}"/>
                    <td>
                        <img src="${pageContext.request.contextPath}/upload/${album.cover}"/><br>
                        <div class="blackTd"><c:out value="${album.title}"/></div><br>
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


    </s:layout-component>
</s:layout-render>
