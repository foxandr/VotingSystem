<%--
  Created by IntelliJ IDEA.
  User: fox
  Date: 30.08.16
  Time: 9:45
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
    <div class="box">
        <div class="row2">
            <hr>
            <h2 class="intro-text text-center"><spring:message code="voting.header"/></h2>
            <hr>
            <div class="col-sm-3 col-md-3">
                <div class="view-box">
                    <div class="form-group">
                        <label class="control-label" for="datatable"><spring:message code="voting.results"></spring:message></label>
                        <table class="table table-stripped display" id="datatable">
                            <thead>
                            <tr>
                                <th><spring:message code="voting.rank"></spring:message></th>
                                <th><spring:message code="restaurants.name"></spring:message></th>
                                <th><spring:message code="voting.numbervotes"></spring:message></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label" for="voting"><spring:message code="voting.process"></spring:message></label>
                    <table class="table display" id="voting">
                        <thead>
                        <tr>
                            <th><spring:message code="restaurants.name"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${restaurants}" var="restaurants">
                        <c:if test="${votingRest == restaurants.id}">
                            <tr class="bg-success" id="votedRest_${restaurants.id}">
                        </c:if>
                        <c:if test="${votingRest != restaurants.id}">
                            <tr id="votedRest_${restaurants.id}">
                        </c:if>
                                <td>
                                    ${restaurants.name}
                                </td>
                                <td>
                                    <a class="btn btn-xs btn-danger" onclick="makeVote(${restaurants.id});"><spring:message code="voting.process"></spring:message></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-sm-3 col-md-3 col-lg-offset-6">
            <img class="img-responsive img-full" src="resources/img/slide-1.jpg" alt="">
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript" src="resources/js/voteDatatables.js"></script>
<script type="text/javascript" src="resources/js/voteUtils.js"></script>
</body>
</html>
