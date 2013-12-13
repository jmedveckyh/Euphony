<%-- 
    Document   : artistform
    Created on : Dec 13, 2013, 4:51:36 PM
    Author     : Medo
--%>

<fmt:bundle basename="StripesResources" /> 

<s:errors/>

<fmt:message key='admin.artistName' var="name"/>

<table style="width:fit-content">
    <tr>
        <th><s:label for="b1" name="${name}"/></th>
        <td><s:text id="b1" name="artist.name"/></td>
    </tr>
</table>
