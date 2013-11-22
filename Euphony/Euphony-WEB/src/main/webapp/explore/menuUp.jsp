<%-- 
    Document   : menuUp
    Created on : 22-Nov-2013, 22:55:32
    Author     : Tomas Smetanka
--%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean"><span><f:message key="menu.songs"/></span></s:link>
<s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="albums"><span><f:message key="menu.albums"/></span></s:link>
<s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="artists"><span><f:message key="menu.artists"/></span></s:link>
<div class="cl"></div>
<br><br>