<%--
  Created by IntelliJ IDEA.
  User: fox
  Date: 30.08.16
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/header.jsp"/>

<body>
<jsp:include page="fragments/bodyPart.jsp"/>

<div class="container">
    <div class="box">
        <div class="row2">
            <div class="col-sm-3 col-md-3">
                <div class="view-box">
                    <spring:message code="app.login"/>
                    <form:form class="navbar-form" role="form" action="spring_security_check" method="post">
                        <div class="form-group">
                            <input type="text" placeholder="Email" class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success"><spring:message code="app.login"/></button>
                    </form:form>
                </div>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-offset-6">
                <img class="img-responsive img-full" src="resources/img/slide-1.jpg" alt="">
            </div>
        </div>
    </div>
</div>
<div class="container">
    <c:if test="${error}">
        <div class="error">
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="message">
            <spring:message code="${message}"/>
        </div>
    </c:if>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
