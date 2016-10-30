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
            <hr>
            <h2 class="intro-text text-center">
                <c:if test="${reg}">
                    <spring:message code="app.regnew"/>
                </c:if>
                <c:if test="${!reg}">
                    <spring:message code="app.profile"/>
                </c:if>
            </h2>
            <hr>
        </div>
        <div class="row">
            <div class="col-sm-6 col-md-6">
                <div class="row col-md-offset-0 col-sm-offset-0">
                    <div class="col-sm-6 col-md-6">
                        <form:form role="form" method="post" id="detailsUserForm">
                            <input type="text" hidden="hidden" id="id" name="id">
                            <div class="form-group">
                                <label for="name"><spring:message code="users.name"/></label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <label for="email"><spring:message code="users.email"/></label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                <c:if test="${errmail != null}">
                                    <span class="text-danger"><spring:message code="exception.demail"/></span>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="password"><spring:message code="users.pass"/></label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="">
                                <c:if test="${errpass != null}">
                                    <span class="text-danger"><spring:message code="exception.dpass"/></span>
                                </c:if>
                            </div>
                            <c:if test="${!reg}">
                                <div class="form-group">
                                    <label for="newpass"><spring:message code="users.newpass"/></label>
                                    <input type="password" class="form-control" id="newpass" name="newpass" placeholder="">
                                    <c:if test="${errnewpass != null}">
                                        <span class="text-danger"><spring:message code="exception.dnewpass"/></span>
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <label for="confirmpass"><spring:message code="users.confirm"/></label>
                                    <input type="password" class="form-control" id="confirmpass" name="confirmpass" placeholder="">
                                </div>
                            </c:if>
                            <button type="button" class="btn btn-primary" onclick="sendData(${reg})">
                                <c:if test="${reg}">
                                    <spring:message code="app.register"/>
                                </c:if>
                                <c:if test="${!reg}">
                                    <spring:message code="common.update"/>
                                </c:if>
                            </button>
                            <c:if test="${reg}">
                                <button type="button" class="btn btn-danger" onclick="javascript:history.back()">
                                    <spring:message code="common.back"/>
                                </button>
                            </c:if>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-6">
                <img class="img-responsive img-full" src="resources/img/slide-2.jpg" alt="">
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript"><jsp:include page="fragments/i18n.jsp"/></script>
<script type="text/javascript" src="resources/js/profileUtils.js"></script>
</body>
</html>
