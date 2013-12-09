<%-- 
    Document   : search
    Created on : 6.12.2013, 19:35:45
    Author     : Branislav Novotny <br.novotny@gmail.com> #396152
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="./layout.jsp" titlekey="search.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.SearchActionBean" var="searchActionBean"/>
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" var="playlistActionBean"/>

        <h3><f:message key="menu.explore"/></h3>
        <%@include file="explore/menuUp.jsp"%>

        <h3><f:message key="search.list.songs"/> "${searchActionBean.phrase}" </h3>

        <%@include file="incl/songsIncl.jsp"%>

        <h3><f:message key="search.list.albums"/> "${searchActionBean.phrase}" </h3>

        <%@include file="incl/albumsIncl.jsp"%>

        <h3><f:message key="search.list.artists"/> "${searchActionBean.phrase}" </h3>

        <%@include file="incl/artistsIncl.jsp"%>

        <h3><f:message key="search.list.playlists"/> "${searchActionBean.phrase}" </h3>

         <%@include file="incl/playlistsIncl.jsp"%>

    </s:layout-component>
</s:layout-render>