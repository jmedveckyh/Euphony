<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h3><f:message key="menu.explore"/></h3>
        <%@include file="menuUp.jsp"%>

        <p>index a albumy</p>

        <table class="basic">
            <tr>
                <th><f:message key="album.id"/></th>
                <th><f:message key="album.name"/></th>
            </tr>
            <c:forEach items="${actionBean.albums}" var="album">
                <tr>
                    <td><c:out value="${album.id}"/></td>
                    <td><c:out value="${album.name}"/></td>
                </tr>
            </c:forEach>
        </table>


    </s:layout-component>
</s:layout-render>
