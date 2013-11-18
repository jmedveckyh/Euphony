<%-- 
    Document   : layout
    Created on : 18-Nov-2013, 02:10:16
    Author     : Tomáš Smetanka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <title><f:message key="${titlekey}"/></title>
        <link rel="stylesheet" type="text/css" href="./style.css" />
        <s:layout-component name="header"/>
    </head>
    <body>
        <div class="navigation">
            <div class="logo"><h1>Euphony</h1></div>
            <div class="cl"></div>
            <div class="search">
                <table border="0">
                    <tr>
                        <td><input type="text" name="search" class="search"></td>
                        <td><input type="submit" value=""></td>
                    </tr>
                </table>
                
            </div>
            <br>some<br>thing<br><br><br><br><br>
        </div>
        <div class="userPanel">
            Login
        </div>
        <div class="content">
            <s:messages/>
            <s:layout-component name="body"/>
        </div>
    </body>
</html>
</s:layout-definition>
