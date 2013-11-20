<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="book.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.GenreActionBean" var="actionBean"/>

        <p><f:message key="genre.list.allbooks"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="book.name"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.genres}" var="genre">
                <tr>
                    <td>${genre.id}</td>
                    <td><c:out value="${genre.author}"/></td>
                    <td>
                     <s:link beanclass="com.musiclibrary.euphonyweb.GenreActionBean" event="edit"><s:param name="genre.id" value="${genre.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="com.musiclibrary.euphonyweb.GenreActionBean">
                            <s:hidden name="genre.id" value="${genre.id}"/>
                            <s:submit name="delete"><f:message key="book.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="com.musiclibrary.euphonyweb.GenreActionBean">
            <fieldset><legend><f:message key="genre.list.newgenre"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Vytvořit novou knihu</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>