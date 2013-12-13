<%-- 
    Document   : servererror
    Created on : Dec 13, 2013, 4:12:08 PM
    Author     : Medo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unavailable Server</title>
    </head>
    <body>
        
    <center>
           <p style="font-size: 12px">User friendly message:</p> 
        <h1 style="font-size: 100px">Unavailable Server!</h1>
        <h1 style="font-size: 100px"><c:out value="${requestScope['javax.servlet.error.message']}"/></h1>
    </center>
    </body>
</html>
