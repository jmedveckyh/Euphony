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

<s:layout-render name="./../layout.jsp" titlekey="menu.genres">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" var="actionBean"/>
            <s:form beanclass="com.musiclibrary.euphonyrest.client.ArtistActionBean" name="requiredForm">
                <s:hidden name="artist.id"/>
                <fieldset><legend><fmt:message key="admin.changeEntries"/></legend>
                    <%@include file="artistform.jsp"%>
                    <s:submit name="save" onclick="return validateArtistForm()"><fmt:message key="save"/></s:submit>
                    <s:submit name="storno"><fmt:message key="storno"/></s:submit>
                    </fieldset>
            </s:form>
    </s:layout-component>
</s:layout-render>
