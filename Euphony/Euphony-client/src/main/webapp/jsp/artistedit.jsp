<%-- 
    Document   : artistedit
    Created on : Dec 13, 2013, 4:51:10 PM
    Author     : Medo
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Locale" %>

<fmt:bundle basename="StripesResources" /> 
<fmt:message key='admin.artistEdit' var="title"/>
<s:layout-render name="layout.jsp" title="${title}">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" var="actionBean"/>
        <center>
            <s:form style="width:400px" beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean">
                <s:hidden name="artist.id"/>
                <fieldset><legend><fmt:message key="admin.changeEntries"/></legend>
                    <%@include file="artistform.jsp"%>
                    <s:submit name="save"><fmt:message key="save"/></s:submit>
                    <s:submit name="storno"><fmt:message key="storno"/></s:submit>
                    </fieldset>
            </s:form>
        </center>
    </s:layout-component>
</s:layout-render>
