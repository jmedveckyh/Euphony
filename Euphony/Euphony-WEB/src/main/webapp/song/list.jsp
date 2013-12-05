<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="song.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.SongActionBean" var="add"/>

        <h3><f:message key="song.list.allsongs"/></h3>

        <s:form beanclass="com.musiclibrary.euphonyweb.SongActionBean">
            <fieldset><legend><f:message key="song.list.newsong"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="song.list.createnewsong"/></s:submit>
                </fieldset>
        </s:form>
        <s:errors/>
        
        <table class="basic">
            <tr>
                <th><f:message key="action.update"/></th>
                <th><f:message key="action.delete"/></th>
                <th><f:message key="song.title"/></th>
                <th><f:message key="song.bitrate"/></th>
                <th><f:message key="song.trackNumber"/></th>
                <th><f:message key="song.comment"/></th>
                <th><f:message key="song.genre"/></th>
                <th><f:message key="song.artist"/></th>
                <th><f:message key="song.album"/></th>
            </tr>
            <c:forEach items="${actionBean.songs}" var="song">
                <tr>
                    <td class="actionTd">
                        <s:link beanclass="com.musiclibrary.euphonyweb.SongActionBean" event="edit">
                            <s:param name="song.id" value="${song.id}"/>
                            <img src="${pageContext.request.contextPath}/img/edit.png" width="25px"/>
                        </s:link>
                    </td>
                    <td class="actionTd">
                        <s:link beanclass="com.musiclibrary.euphonyweb.SongActionBean" event="delete">
                            <s:param name="song.id" value="${song.id}"/>
                            <img src="${pageContext.request.contextPath}/img/delete.png" width="25px"/>
                        </s:link>
                    </td>
                    <td><c:out value="${song.title}"/></td>
                    <td><c:out value="${song.bitrate}"/></td>
                    <td><c:out value="${song.trackNumber}"/></td>
                    <td><c:out value="${song.comment}"/></td>
                    <td><c:out value="${song.genre.name}"/></td>
                    <td><c:out value="${song.artist.name}"/></td>
                    <td><c:out value="${song.album.title}"/></td>
                </tr>
            </c:forEach>
        </table>           
    </s:layout-component>
</s:layout-render>