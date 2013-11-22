<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="song.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.SongActionBean" var="actionBean"/>

        <p><f:message key="song.list.allsongs"/></p>

        <table class="basic">
            <tr>
                <th><f:message key="song.id"/></th>
                <th><f:message key="song.title"/></th>
                <th><f:message key="song.bitrate"/></th>
                <th><f:message key="song.trackNumber"/></th>
                <th><f:message key="song.comment"/></th>
                <th><f:message key="song.genre"/></th>
                <th><f:message key="song.artist"/></th>
                <th><f:message key="song.album"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.songs}" var="song">
                <tr>
                    <td><c:out value="${song.id}"/></td>
                    <td><c:out value="${song.title}"/></td>
                    <td><c:out value="${song.bitrate}"/></td>
                    <td><c:out value="${song.trackNumber}"/></td>
                    <td><c:out value="${song.comment}"/></td>
                    <td><c:out value="${song.genre.name}"/></td>
                    <td><c:out value="${song.artist.name}"/></td>
                    <td><c:out value="${song.album.title}"/></td>
                    <td>
                        <s:link beanclass="com.musiclibrary.euphonyweb.SongActionBean" event="edit"><s:param name="song.id" value="${song.id}"/><f:message key="song.list.edit"/></s:link>
                        </td>
                        <td>
                        <s:form beanclass="com.musiclibrary.euphonyweb.SongActionBean">
                            <s:hidden name="song.id" value="${song.id}"/>
                            <s:submit name="delete"><f:message key="song.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
            
        <s:form beanclass="com.musiclibrary.euphonyweb.SongActionBean">
            <fieldset><legend><f:message key="song.list.newsong"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="song.list.createnewsong"/></s:submit>
                </fieldset>
        </s:form>
           
    </s:layout-component>
</s:layout-render>