<%@page contentType="text/javascript" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
var i18n = [];
<c:forEach var='key' items='<%=new String[]{"users.add", "users.edit", "common.edit", "common.delete", "common.recover", "common.confirm", "common.enpass"}%>'>

i18n['${key}'] = '<spring:message code="${key}" javaScriptEscape="true"/>';
</c:forEach>