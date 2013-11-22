<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="artist.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.ArtistActionBean" var="actionBean"/>

        <p><f:message key="artist.list.allartists"/></p>

        <table class="basic">
            <tr>
                <th><f:message key="artist.id"/></th>
                <th><f:message key="artist.name"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.artists}" var="artist">
                <tr>
                    <td><c:out value="${artist.id}"/></td>
                    <td><c:out value="${artist.name}"/></td>
                    <td>
                        <s:link beanclass="com.musiclibrary.euphonyweb.ArtistActionBean" event="edit"><s:param name="artist.id" value="${artist.id}"/><f:message key="artist.list.edit"/></s:link>
                        </td>
                        <td>
                        <s:form beanclass="com.musiclibrary.euphonyweb.ArtistActionBean">
                            <s:hidden name="artist.id" value="${artist.id}"/>
                            <s:submit name="delete"><f:message key="artist.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="com.musiclibrary.euphonyweb.ArtistActionBean">
            <fieldset><legend><f:message key="artist.list.newartist"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="artist.list.createnewartist"/></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>