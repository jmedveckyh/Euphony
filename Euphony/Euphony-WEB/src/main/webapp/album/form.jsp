<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="StripesResources"/>
<fmt:message key='album.title' var="title"/>
<fmt:message key='album.cover' var="cover"/>
<fmt:message key='album.comment' var="comment"/>
<fmt:message key='album.releaseDate' var="releaseDate"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom-theme/jquery-ui-1.10.3.custom.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.min.js"></script>
<script>
  $(function() {
    $( "#datepicker" ).datepicker({changeYear: true, yearRange: "-100:+0"});
    $( "#datepicker" ).datepicker( "option", "showAnim", "slideDown" );
    $( "#datepicker" ).datepicker( "option", "dateFormat", "dd.mm.yy" );
  });
  </script>
  
<table>
    <tr>
        <th><s:label for="b1" name="${title}"/></th>
        <td><s:text id="b1" name="album.title" size="24"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="${cover}"/></th>
        <td><c:if test="${not empty edit.album.cover}">
            <img src="${pageContext.request.contextPath}/upload/<c:out value="${edit.album.cover}"/>" width="100" height="100"/>
            <s:submit name="deleteCover"><f:message key="album.form.deleteCover"/></s:submit>
            </c:if>
            <s:file id="b2" name="cover"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="${comment}"/></th>
        <td><s:textarea id="b3" name="album.comment"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="${releaseDate}"/></th>
        <td><s:text id="datepicker" name="releaseDate" size="24"/></td>
    </tr>
</table>
