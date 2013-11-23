<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="StripesResources"/>
<s:errors/>
<fmt:message key='album.title' var="title"/>
<fmt:message key='album.cover' var="cover"/>
<fmt:message key='album.comment' var="comment"/>
<fmt:message key='album.releaseDate' var="releaseDate"/>

<table>
    <tr>
        <th><s:label for="b1" name="${title}"/></th>
        <td><s:text id="b1" name="album.title"/></td>
    </tr>
        <tr>
        <th><s:label for="b2" name="${cover}"/></th>
        <td><s:text id="b2" name="album.cover"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="${comment}"/></th>
        <td><s:text id="b3" name="album.comment"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="${releaseDate}"/></th>
        <td><s:text id="b4" name="album.releaseDate"/></td>
    </tr>
</table>