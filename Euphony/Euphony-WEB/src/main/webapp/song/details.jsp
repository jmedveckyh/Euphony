<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:useActionBean beanclass="com.musiclibrary.euphonyweb.SongActionBean" var="details"/>
<s:layout-render name="/layout.jsp" titlekey="${details.song.title}">
    <s:layout-component name="body">
        <h1>${details.song.title}</h1>
        <p><f:message key="song.bitrate"/>: ${details.song.bitrate} Kbps</p>
        <p><f:message key="song.trackNumber"/>: ${details.song.trackNumber}</p>
        <p><f:message key="song.genre"/>: ${details.song.genre.name} - odkaz na genre info</p>
        <p><f:message key="song.artist"/>: ${details.song.artist.name} - odkaz na ertist info</p>
        <p><f:message key="song.album"/>: ${details.song.album.title} - odkaz na album info + obrazok</p>
    </s:layout-component>
</s:layout-render>
