<%-- 
    Document   : genreedit
    Created on : Dec 13, 2013, 4:51:28 PM
    Author     : Medo, Tomáš Smetanka
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
        <s:useActionBean beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" var="actionBean"/>
            <s:form beanclass="com.musiclibrary.euphonyrest.client.GenreActionBean" name="requiredForm">
                <s:hidden name="genre.id"/>
                <fieldset><legend><fmt:message key="admin.changeEntries"/></legend>
                    <%@include file="genreform.jsp"%>
                    <s:submit name="save" onclick="return validateGenreForm()"><fmt:message key="save"/></s:submit>
                    <s:submit name="storno"><fmt:message key="storno"/></s:submit>
                    </fieldset>
            </s:form>
    </s:layout-component>
</s:layout-render>
