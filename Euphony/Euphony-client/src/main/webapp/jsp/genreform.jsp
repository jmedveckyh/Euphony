<%-- 
    Document   : genreform
    Created on : Dec 13, 2013, 4:51:51 PM
    Author     : Medo
--%>

<fmt:bundle basename="StripesResources" /> 

<s:errors/>

<fmt:message key='admin.genreName' var="name"/>

<table style="width:fit-content">
    <tr>
        <th><s:label for="b1" name="${name}"/></th>
        <td><s:text id="b1" name="genre.name"/></td>
    </tr>
</table>
