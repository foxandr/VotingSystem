<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../fragments/header.jsp"/>

<body>
<jsp:include page="../fragments/bodyPart.jsp"/>

<div class="container">
    <div class="box">
        <div class="row col-md-offset-0 col-sm-offset-0">
        <br>
        <h6><span class="alert-link"><spring:message code="exception.message"/></span></h6>
<!--        <h4>Application error: </h4>
        <h2>${exception.message}</h2>
<!--
<c:forEach items="${exception.stackTrace}" var="stackTrace">
    ${stackTrace}
</c:forEach>
-->
        </div>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>