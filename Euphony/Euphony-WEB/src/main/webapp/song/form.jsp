<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="StripesResources"/>
<s:errors/>
<fmt:message key='song.title' var="title"/>
<fmt:message key='song.bitrate' var="bitrate"/>
<fmt:message key='song.trackNumber' var="trackNumber"/>
<fmt:message key='song.comment' var="comment"/>
<fmt:message key='song.genre' var="genre"/>
<fmt:message key='song.album' var="album"/>
<fmt:message key='song.artist' var="artist"/>

<table>
    <tr>
        <th><s:label for="b1" name="${title}"/></th>
        <td><s:text id="b1" name="song.title"/></td>
    </tr>
        <tr>
        <th><s:label for="b2" name="${bitrate}"/></th>
        <td><s:text id="b2" name="song.bitrate"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="${trackNumber}"/></th>
        <td><s:text id="b3" name="song.trackNumber"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="${comment}"/></th>
        <td><s:text id="b4" name="song.comment"/></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="${genre}"/></th>
        <td>
            <s:select id="b5" name="genre">
            <s:options-collection collection="${actionBean.genres}" label="name" value="id"/>
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="b6" name="${album}"/></th>
        <td>
            <s:select id="b6" name="album">
            <s:options-collection collection="${actionBean.albums}" label="title" value="id"/>
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="b7" name="${artist}"/></th>
        <td>
            <s:select id="b7" name="artist">
            <s:options-collection collection="${actionBean.artists}" label="name" value="id"/>
            </s:select>
        </td>
    </tr>
</table>