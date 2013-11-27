<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="song.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.musiclibrary.euphonyweb.SongActionBean" var="edit"/>

        <s:form beanclass="com.musiclibrary.euphonyweb.SongActionBean">
            <s:hidden name="song.id"/>
            <fieldset><legend><f:message key="song.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="save"><f:message key="song.edit.save"/></s:submit>
                <s:submit name="cancel"><f:message key="song.edit.cancel"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>