<%-- 
    Document   : genreform
    Created on : Dec 13, 2013, 4:51:51 PM
    Author     : Medo
--%>

<s:errors/>
<fmt:message key='admin.genreName' var="name"/>
<table class="addEditForm">
    <tr>
        <th><s:label for="pn" name="${name}"/></th>
        <td><s:text id="pn" name="genre.name"/></td>
    </tr>
</table>
