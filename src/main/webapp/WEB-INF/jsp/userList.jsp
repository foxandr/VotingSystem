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
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/header.jsp"/>

<body>
<jsp:include page="fragments/bodyPart.jsp"/>

<div class="container">
    <div class="row">
        <div class="box">
            <hr>
            <h2 class="intro-text text-center"><spring:message code="voting.header"/></h2>
            <hr>
            <div class="col-lg-6">
                <div class="view-box">
                    <a class="btn btn-sm btn-info" onclick="add('users.add')"><spring:message code="users.add"/></a>
                    <table class="table table-striped display" id="datatable">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Roles</th>
                            <th>Registered</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="col-lg-6 col-lg-offset-6">
                <img class="img-responsive img-full" src="resources/img/slide-1.jpg" alt="">
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
