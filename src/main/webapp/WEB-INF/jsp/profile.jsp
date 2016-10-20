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
                    <form:form class="form-horizontal" method="post" id="detailsUserForm">
                        <input type="text" hidden="hidden" id="id" name="id">

                        <div class="form-group">
                            <label for="name" class="control-label col-xs-3"><spring:message code="users.name"/></label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="control-label col-xs-3"><spring:message code="users.email"/></label>

                            <div class="col-xs-9">
                                <input type="email" class="form-control" id="email" name="email" placeholder="email">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="control-label col-xs-3"><spring:message code="users.pass"/></label>

                            <div class="col-xs-9">
                                <input type="password" class="form-control" id="password" name="password" placeholder="">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-9">
                                <button type="submit" class="btn btn-primary" onclick="sendData(${reg})">
                                    <c:if test="${reg}">
                                        <spring:message code="app.register"/>
                                    </c:if>
                                    <c:if test="${!reg}">
                                        <spring:message code="common.update"/>
                                    </c:if>
                                </button>
                            </div>
                        </div>
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
<script type="text/javascript" src="resources/js/profileUtils.js"></script>
</body>
</html>
