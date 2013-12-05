<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="song.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.AlbumActionBean" var="edit"/>
        
        <h3><f:message key="album.list.editalbum"/></h3>
        
        <s:errors/>
        <s:form beanclass="com.musiclibrary.euphonyweb.AlbumActionBean">
            <s:hidden name="album.id"/>
            <fieldset><legend><f:message key="album.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="save"><f:message key="album.edit.save"/></s:submit>
                <s:submit name="cancel"><f:message key="album.edit.cancel"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>