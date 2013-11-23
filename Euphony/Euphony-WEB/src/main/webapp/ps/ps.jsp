<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="playlist.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" var="actionBean"/>  

        <table class="basic">
            <tr>
                <th><f:message key="playlist.id"/></th>
                <th><f:message key="playlist.name"/></th>
            </tr>
            <c:forEach items="${actionBean.playlists}" var="playlist">
                <tr>
                    <td><c:out value="${playlist.id}"/></td>
                    <td><c:out value="${playlist.name}"/></td>
                </tr>
            </c:forEach>
        </table>
<%--
        <s:form beanclass="com.musiclibrary.euphonyweb.GenreActionBean">
            <fieldset><legend><f:message key="genre.list.newgenre"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="genre.list.createnewgenre"/></s:submit>
                </fieldset>
        </s:form>--%>
    </s:layout-component>
</s:layout-render>