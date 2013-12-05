<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="artist.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.ArtistActionBean" var="actionBean"/>

        <h3><f:message key="artist.list.allartists"/></h3>

        <s:form beanclass="com.musiclibrary.euphonyweb.ArtistActionBean">
            <fieldset><legend><f:message key="artist.list.newartist"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="artist.list.createnewartist"/></s:submit>
            </fieldset>
        </s:form>
        <s:errors/>

        <table class="basic">
            <tr>
                <th><f:message key="action.update"/></th>
                <th><f:message key="action.delete"/></th>
                <th><f:message key="genre.name"/></th>
            </tr>
            <c:forEach items="${actionBean.artists}" var="artist">
                <tr>
                    <td class="actionTd">
                        <s:link beanclass="com.musiclibrary.euphonyweb.ArtistActionBean" event="edit">
                            <s:param name="artist.id" value="${artist.id}"/>
                            <img src="${pageContext.request.contextPath}/img/edit.png" width="25px"/>
                        </s:link>
                    </td>
                    <td class="actionTd">
                        <s:link beanclass="com.musiclibrary.euphonyweb.ArtistActionBean" event="delete">
                            <s:param name="artist.id" value="${artist.id}"/>
                            <img src="${pageContext.request.contextPath}/img/delete.png" width="25px"/>
                        </s:link>
                    </td>
                    <td><c:out value="${artist.name}"/></td>
                </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>