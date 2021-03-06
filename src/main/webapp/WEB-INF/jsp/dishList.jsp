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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/header.jsp"/>

<body>
<jsp:include page="fragments/bodyPart.jsp"/>

<div class="container">
    <div class="box">
        <div class="row">
            <hr>
            <h2 class="intro-text text-center">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <spring:message code="dishes.header"/>
                </sec:authorize>
                <sec:authorize access="!hasRole('ROLE_ADMIN')">
                    <spring:message code="dishes.userheader"/>
                </sec:authorize>
            </h2>
            <hr>
        </div>
        <div class="row">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="col-sm-7 col-md-7">
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ADMIN')">
            <div class="col-sm-6 col-md-6">
        </sec:authorize>
                <div class="row col-md-offset-0 col-sm-offset-0">
                    <div class="col-sm-3 col-md-3">
                        <label class="control-label" for="rest_id"><spring:message code="dishes.rest"></spring:message></label><br>
                        <select id="rest_id">
                            <c:forEach items="${restNames}" var="rest">
                                <option value="${rest.id}">${rest.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-5 col-md-5">
                        <label class="control-label" for="updated"><spring:message code="dishes.date"></spring:message></label><br>
                        <input type='text' id="updated"/>
                    </div>
                    <div class="col-sm-4 col-md-4">
                       <br><a class="btn btn-sm btn-primary" onclick="getByDate();"><spring:message code="common.choose"></spring:message></a>
                    </div>
                </div>
                <div class="row col-md-offset-0 col-sm-offset-0">
                    <hr>
                </div>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="row col-md-offset-0 col-sm-offset-0">
                        <a class="btn btn-sm btn-info" onclick="addDish('dishes.add')"><spring:message code="dishes.add"/></a>
                    </div>
                </sec:authorize>
                <div class="row col-md-offset-0 col-sm-offset-0">
                    <table class="table table-striped display" id="datatable">
                        <thead>
                        <tr>
                            <th><spring:message code="dishes.name"/></th>
                            <th><spring:message code="dishes.price"/></th>
                            <th><spring:message code="dishes.updated"/></th>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <th></th>
                                <th></th>
                            </sec:authorize>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="col-sm-5 col-md-5">
            </sec:authorize>
            <sec:authorize access="!hasRole('ROLE_ADMIN')">
                <div class="col-sm-6 col-md-6">
            </sec:authorize>
                <img class="img-responsive img-full" src="resources/img/slide-3.jpg" alt="">
            </div>
        </div>
    </div>
</div>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="modal fade" id="editRow">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title" id="modalTitle"></h2>
                </div>
                <div class="modal-body">
                    <form:form class="form-horizontal" method="post" id="detailsDishForm">
                        <input type="text" hidden="hidden" id="id" name="id">
                        <input type="text" hidden="hidden" id="rest_id_upd" name="rest_id_upd">
                        <div class="form-group">
                            <label for="name" class="control-label col-xs-3"><spring:message code="dishes.name"/></label>
                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="price" class="control-label col-xs-3"><spring:message code="dishes.price"/></label>
                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="price" name="price" placeholder="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-9">
                                <button type="submit" class="btn btn-primary"><spring:message code="common.save"/></button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>

<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript"><jsp:include page="fragments/i18n.jsp"/></script>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <script type="text/javascript" src="resources/js/dishDatatables.js"></script>
    <script type="text/javascript" src="resources/js/dishUtils.js"></script>
</sec:authorize>
<sec:authorize access="!hasRole('ROLE_ADMIN')">
    <script type="text/javascript" src="resources/js/dishForUsers.js"></script>
</sec:authorize>

</body>
</html>
