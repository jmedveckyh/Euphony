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
        <td>
            <s:select name="song.bitrate">  
                <s:option label="" value=""/>
                <s:option label="32 kbps" value="32"/>
                <s:option label="64 kbps" value="64"/>
                <s:option label="96 kbps" value="96"/>
                <s:option label="128 kbps" value="128"/>
                <s:option label="160 kbps" value="160"/>
                <s:option label="192 kbps" value="192"/> 
                <s:option label="256 kbps" value="256"/>
                <s:option label="320 kbps" value="320"/>
                <s:option label="Lossless" value="2500"/>
            </s:select>  
        </td>

    </tr>
    <tr>
        <th><s:label for="b3" name="${trackNumber}"/></th>
        <td><s:text id="b3" name="song.trackNumber"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="${comment}"/></th>
        <td><s:textarea id="b4" name="song.comment"/></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="${genre}"/></th>
        <td>
            <s:select id="b5" name="genre">
                <s:option label="" value=""/>
                <s:options-collection collection="${actionBean.genres}" label="name" value="id"/>
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="b6" name="${album}"/></th>
        <td>
            <s:select id="b6" name="album">
                <s:option label="" value=""/>
                <s:options-collection collection="${actionBean.albums}" label="title" value="id"/>
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="b7" name="${artist}"/></th>
        <td>
            <s:select id="b7" name="artist">
                <s:option label="" value=""/>
                <s:options-collection collection="${actionBean.artists}" label="name" value="id"/>
            </s:select>
        </td>
    </tr>
</table>
