<%-- 
    Document   : explore
    Created on : Dec 13, 2013, 4:12:08 PM
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
<fmt:message key='menu.play' var="play" />
<s:layout-render name="layout.jsp" title="${explore}">

    <s:layout-component name="telo">     
        <s:errors/>    
        
    </s:layout-component>
</s:layout-render>
