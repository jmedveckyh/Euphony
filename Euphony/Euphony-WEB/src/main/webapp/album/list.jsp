<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="album.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" var="actionBean"/>

        <p><f:message key="album.list.allalbums"/></p>

        <table class="basic">
            <tr>
                <th><f:message key="album.id"/></th>
                <th><f:message key="album.title"/></th>
                <th><f:message key="album.cover"/></th>
                <th><f:message key="album.comment"/></th>
                <th><f:message key="album.releaseDate"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.albums}" var="album">
                <tr>
                    <td><c:out value="${album.id}"/></td>
                    <td><c:out value="${album.title}"/></td>
                    <td><c:out value="${album.cover}"/></td>
                    <td><c:out value="${album.comment}"/></td>
                    <td><c:out value="${album.releaseDate.dayOfMonth}.${album.releaseDate.monthOfYear}.${album.releaseDate.year}"/></td>
                    <td>
                        <s:link beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" event="edit"><s:param name="album.id" value="${album.id}"/><f:message key="album.list.edit"/></s:link>
                        </td>
                        <td>
                        <s:form beanclass="com.musiclibrary.euphonyweb.AlbumActionBean">
                            <s:hidden name="album.id" value="${album.id}"/>
                            <s:submit name="delete"><f:message key="album.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <s:errors/>
        <s:form beanclass="com.musiclibrary.euphonyweb.AlbumActionBean">
            <fieldset><legend><f:message key="album.list.newalbum"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="album.list.createnewalbum"/></s:submit>
                </fieldset>
        </s:form>
           
    </s:layout-component>
</s:layout-render>
