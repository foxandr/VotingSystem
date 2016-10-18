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
<%@ taglib prefix="vt" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/header.jsp"/>

<body>
<jsp:include page="fragments/bodyPart.jsp"/>

<div class="container">
    <div class="row">
        <div class="box">
            <hr>
            <h2 class="intro-text text-center">
            <c:if test="${reg}">
                <spring:message code="app.regnew"/>
            </c:if>
            <c:if test="${!reg}">
                ${userTo.name}<spring:message code="app.profile"/>
            </c:if>
            </h2>
            <hr>
            <div class="col-sm-3 col-md-3">
                <div class="view-box">
                    <form:form modelAttribute="userTo" class="form-horizontal" method="post" action="${reg ? 'register' : 'profile'}" charset="UTF-8" acceptCharset="UTF-8">
                        <%--<vt:inputField name="name" label="Name"/>--%>
                        <%--<vt:inputField name="email" label="Email"/>--%>
                        <%--<vt:inputField name="password" label="Password" inputType="password"/>--%>
                        <button type="submit" class="btn btn-primary">
                            <c:if test="${reg}">
                                <spring:message code="app.register"/>
                            </c:if>
                            <c:if test="${!reg}">
                                ${userTo.name}<spring:message code="common.update"/>
                            </c:if>
                        </button>
                    </form:form>
                </div>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-offset-6">
                <img class="img-responsive img-full" src="resources/img/slide-1.jpg" alt="">
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
