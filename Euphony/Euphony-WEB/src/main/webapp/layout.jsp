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
            <div class="menu">
            <ul>
                <li><a href="#"><span><f:message key="menu.explore"/></span></a></li>
                <li><a href="#"><span><f:message key="menu.newplaylist"/></span></a></li>
            </ul>
                <h2><f:message key="menu.playlists"/></h2>
                <hr>
                <ul>
                    <li><a href="#"><span>for each</span></a></li>
                    <li><a href="#"><span>for each</span></a></li>
                    <li><a href="#"><span>for each</span></a></li>
                    <li><a href="#"><span>for each</span></a></li>
                    <li><a href="#"><span>for each</span></a></li>
                    <li><a href="#"><span>for each</span></a></li>
            </div>
        </div>
        <div class="userPanel">
        <ul>
            <li class="drop">
                <a href="#">Admin</a>
                <div class="dropdownContain">
                    <div class="dropOut">
                        <div class="triangle"></div>
                        <ul>
                            <li><f:message key="menu.admin.newgenre"/></li>
                            <li><f:message key="menu.admin.newartist"/></li>
                            <li><f:message key="menu.admin.newalbum"/></li>
                            <li><f:message key="menu.admin.newsong"/></li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
        </div>
        <div class="content">
            <s:messages/>
            <s:layout-component name="body"/>
        </div>
    </body>
</html>
</s:layout-definition>
