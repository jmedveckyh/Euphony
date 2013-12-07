<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:useActionBean beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" var="details"/>
<s:layout-render name="/layout.jsp" titlekey="${details.album.title}">
    <s:layout-component name="body">
        <h1>${details.album.title}</h1>
        <img src="${pageContext.request.contextPath}/upload/<c:out value="${details.album.cover}"/>"/>
        <p><f:message key="album.comment"/>: ${details.album.comment}</p>
        <p><f:message key="album.releaseDate"/>: ${details.album.releaseDate}</p>
        <p>SONGS:</p>
        <p>GENRES:</p>
        <p>ARTISTS:</p>
    </s:layout-component>
</s:layout-render>
