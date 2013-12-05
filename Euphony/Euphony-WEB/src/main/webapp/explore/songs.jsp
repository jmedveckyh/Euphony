<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h3><f:message key="menu.explore"/></h3>
        <%@include file="menuUp.jsp"%>

        <s:errors/>
        <table class="basic">
            <tr>
                <th></th>
                <th><f:message key="song.title"/></th>
                <th><f:message key="song.artist"/></th>
                <th><f:message key="song.album"/></th>
            </tr>
            <s:form beanclass="com.musiclibrary.euphonyweb.Song2PlaylistActionBean">
                <c:forEach items="${actionBean.songs}" var="song">
                    <tr>
                        <td><s:checkbox name="selectedSongs" value="${song.id}"/></td>
                        <td class="blackTd"><c:out value="${song.title}"/></td>
                        <td class="silverTd"><c:out value="${song.artist.name}"/></td>
                        <td class="silverTd"><c:out value="${song.album.title}"/></td>
                    </tr>   
                </c:forEach>
            </table>
            <div class="dropdown">
                <s:select name="selectedPlaylist">
                    <s:option value=""/>
                    <s:options-collection collection="${actionBean.playlists}" label="name" value="id"/>
                </s:select>
                <s:hidden name="title" value="${song.title}"/>
                <s:submit name="song2playlist"><f:message key="add.song.to.playlist"/></s:submit>
                <s:errors field="selectedSongs"/>
            </s:form>
        </div>
        <div class="cl"></div>
    </s:layout-component>
</s:layout-render>
