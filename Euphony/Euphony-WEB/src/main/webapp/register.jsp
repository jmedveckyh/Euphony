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
<f:bundle basename="StripesResources"/>
<f:message key='login.name' var="username"/>
<f:message key='login.password' var="password"/>
<f:message key='register.confirmPass' var="confirmPass"/>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <title><f:message key='register.title'/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
        <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/global.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/random-bg.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="dots">
            <s:useActionBean beanclass="com.musiclibrary.euphonyweb.AuthActionBean" var="actionBean"/>
            <s:form beanclass="com.musiclibrary.euphonyweb.AuthActionBean" name="registerWithPass" onsubmit="return validatePasswordMinChars()">
                <div class="login">
                    <div class="logo"><h1>Euphony</h1></div>
                    <h3><f:message key="register"/></h3>
                    <s:errors/>
                    <table>
                        <tr>
                            <td class="labelTdLog"><s:label for="b1" name="${username}"/></td>
                            <td><s:text id="b1" name="username"/></td>
                        </tr>
                        <tr>
                            <td class="labelTdLog"><s:label for="b2" name="${password}"/> (<f:message key="min.chars"/>)</td>
                            <td><s:password id="b2" name="password"/></td>
                        </tr>
                        <tr>
                            <td class="labelTdLog"><s:label for="b3" name="${confirmPass}"/></td>
                            <td><s:password id="b3" name="passwordConfirm"/></td>
                        </tr>
                        <tr>
                            <td class="labelTdLog"></td>
                            <td><s:submit name="submitRegister"><f:message key="register.do"/></s:submit></td>
                            </tr>
                        </table>
                            <s:link beanclass="com.musiclibrary.euphonyweb.AuthActionBean" event="login"><f:message key="register.back"/></s:link>                        </fieldset>
                </s:form>
            </div>
        </div>
    </body>
</html>

