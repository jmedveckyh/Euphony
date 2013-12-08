<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="album.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" var="actionBean"/>

        <h3><f:message key="album.list.allalbums"/></h3>

        <s:form beanclass="com.musiclibrary.euphonyweb.AlbumActionBean">
            <fieldset><legend><f:message key="album.list.newalbum"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="album.list.createnewalbum"/></s:submit>
                </fieldset>
        </s:form>
        <s:errors/>

        <table class="basic">
            <tr>                
                <th><f:message key="action.update"/></th>
                <th><f:message key="action.delete"/></th>
                <th><f:message key="album.cover"/></th>
                <th><f:message key="album.title"/></th>
                <th><f:message key="album.comment"/></th>
                <th><f:message key="album.releaseDate"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.albums}" var="album">
                <tr>
                    <td class="actionTd">
                        <s:link beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" event="edit">
                            <s:param name="album.id" value="${album.id}"/>
                            <img src="${pageContext.request.contextPath}/img/edit.png" width="25px"/>
                        </s:link>
                    </td>
                    <td class="actionTd">
                        <s:link beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" event="delete">
                            <s:param name="album.id" value="${album.id}"/>
                            <img src="${pageContext.request.contextPath}/img/delete.png" width="25px"/>
                        </s:link>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty album.cover}">
                                <img src="${pageContext.request.contextPath}/upload/<c:out value="${album.cover}"/>" width="100" height="100"/>
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/upload/nocover.png" width="100" height="100"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${album.title}"/></td>
                    <td><c:out value="${album.comment}"/></td>
                    <td><c:out value="${album.releaseDate.dayOfMonth}.${album.releaseDate.monthOfYear}.${album.releaseDate.year}"/></td>
                </tr>
            </c:forEach>
        </table>           
    </s:layout-component>
</s:layout-render>
