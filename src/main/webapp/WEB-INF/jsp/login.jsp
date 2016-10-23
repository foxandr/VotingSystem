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
        <div class="row">
            <div class="col-sm-6 col-md-6">
                <div class="row col-md-offset-0 col-sm-offset-0">
                    <div class="col-sm-5 col-md-5">
                        <form:form role="form" action="spring_security_check" method="post">
                            <div class="form-group">
                                <label for="username"><spring:message code="app.email"/></label>
                                <input type="text" id="username" placeholder="Email" class="form-control" name="username">
                            </div>
                            <div class="form-group">
                                <label for="password"><spring:message code="app.pass"/></label>
                                <input type="password" id="password" placeholder="Password" class="form-control" name="password">
                            </div>
                            <button type="submit" class="btn btn-success"><spring:message code="app.login"/></button>
                            <a class="btn btn-primary" role="button" href="register"><spring:message code="app.register"/></a>
                        </form:form>
                    </div>
                </div>
                <div class="row col-md-offset-0 col-sm-offset-0">
                    <div class="col-sm-5 col-md-5">
                        <c:if test="${error}">
                            <div class="error">
                                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                            </div>
                        </c:if>
                        <c:if test="${not empty message}">
                            <div class="has-error">
                                <spring:message code="${message}"/>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-6">
                <img class="img-responsive img-full" src="resources/img/slide-1.jpg" alt="">
            </div>
        </div>

</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
