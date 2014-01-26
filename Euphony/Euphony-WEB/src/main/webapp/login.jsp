<%-- 
    Document   : login
    Created on : 25.1.2014, 23:07:41
    Author     : Sebastian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <title><f:message key='login.title'/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
        <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/global.js" type="text/javascript"></script>
    </head>
    <body>
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.AuthActionBean" var="actionBean"/>
        <s:errors>
            <s:errors-header><p style="color: red;"><fmt:message key='index.validationerror'/></p></s:errors-header>
            <s:individual-error/>
            <s:errors-footer><br /></s:errors-footer>
</s:errors>
<s:form beanclass="com.musiclibrary.euphonyweb.AuthActionBean">
    <fieldset><legend><f:message key="login"/></legend>
        <table>
            <tr>
                <td class="labelTd"><s:label for="b1" name="name"/></td>
                <td><s:text id="b1" name="login.name"/></td>
            </tr>
            <tr>
                <td class="labelTd"><s:label for="b2" name="name"/></td>
                <td><s:password id="b2" name="login.password"/></td>
            </tr>
        </table>
        <s:submit name="submitLogin"><f:message key="login"/></s:submit>
        <s:submit name="submitRegister"><f:message key="login.register"/></s:submit>
        </fieldset>
</s:form>
</body>
</html>
