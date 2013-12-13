<%-- 
    Document   : artistform
    Created on : Dec 13, 2013, 4:51:36 PM
    Author     : Medo
--%>

<s:errors/>
<fmt:message key='admin.artistName' var="name"/>
<table class="addEditForm">
    <tr>
        <th><s:label for="pn" name="${name}"/></th>
        <td><s:text id="pn" name="artist.name"/></td>
    </tr>
</table>
