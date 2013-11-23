<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="genre.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" var="actionBean"/>
        <h3><c:out value="${actionBean.playlist.name}"/></h3>

        <table border="0" class="playlistEditSubMenu">
            <tr>
                <td>
                    <s:link beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" event="edit">
                        <s:param name="playlist.id" value="${actionBean.playlist.id}"/>
                        <a href="javascript:void(0);" onclick="javascript:showDivEdit();">
                            <img src="${pageContext.request.contextPath}/img/edit.png" width="35px">
                        </a>
                    </s:link>
                            
                </td>
                <td>
                    <s:link beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean" event="delete">
                        <s:param name="playlist.id" value="${actionBean.playlist.id}"/>
                        <img src="${pageContext.request.contextPath}/img/delete.png" width="35px">
                    </s:link>            
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <s:form beanclass="com.musiclibrary.euphonyweb.PlaylistActionBean">
                        <div id="quickAddPlaylistEdit">
                            <s:hidden name="${actionBean.playlist.id}"/>
                            <s:text class="quickAddPlaylist" name="playlist.name" value="${actionBean.playlist.name}"/>
                            <s:submit class="quickAddPlaylistSubmit" name="edit">
                                <f:message key="playlist.edit"/>
                            </s:submit>
                        </div>
                    </s:form>
                </td>
            </tr>
        </table>
        <div class="cl"><br><br></div>
        


        ID: <c:out value="${actionBean.playlist.id}"/>
        <br>
        Nazov: <c:out value="${actionBean.playlist.name}"/>


    </s:layout-component>
</s:layout-render>