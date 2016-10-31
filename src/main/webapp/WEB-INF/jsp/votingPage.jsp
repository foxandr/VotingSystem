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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/header.jsp"/>

<body>
<jsp:include page="fragments/bodyPart.jsp"/>

<div class="container">
    <div class="box">
        <div class="row">
            <hr>
            <h2 class="intro-text text-center"><spring:message code="voting.header"/></h2>
            <hr>
        </div>
        <div class="row">
            <div class="col-sm-7 col-md-7">
                <div class="row col-md-offset-0 col-sm-offset-0">
                    <div class="col-sm-6 col-md-6">
                        <label class="control-label" for="datatable"><spring:message code="voting.results"></spring:message></label>
                        <table class="table table-stripped table-condensed table-hover dataTable no-footer" role="grid" id="datatable">
                            <thead>
                            <tr>
                                <th><spring:message code="voting.rank"></spring:message></th>
                                <th><spring:message code="restaurants.name"></spring:message></th>
                                <th><spring:message code="voting.numbervotes"></spring:message></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <c:if test="${canVote}">
                            <label class="control-label" for="voting"><spring:message code="voting.process"></spring:message>:</label>
                            <table class="table table-stripped table-condensed table-hover dataTable no-footer" role="grid" id="voting">
                                <thead>
                                <tr>
                                    <th><spring:message code="restaurants.name"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <c:forEach items="${restaurants}" var="restaurants">
                                        <tr id="votedRest_${restaurants.id}" role="row" <c:if test="${votingRest == restaurants.id}">class="success"</c:if>>
                                            <td>
                                                ${restaurants.name}
                                            </td>
                                            <td>
                                                <a id="votedLink_${restaurants.id}" class="btn btn-xs btn-primary <c:if test="${votingRest == restaurants.id}">hidden</c:if>" onclick="makeVote(${restaurants.id});">
                                                    <spring:message code="voting.process"></spring:message>
                                                </a>
                                            </td>
                                        </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                        <c:if test="${!canVote}">
                            <span class="text-danger"><spring:message code="voting.cantvote"/></span>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-sm-5 col-md-5">
                <img class="img-responsive img-full" src="resources/img/slide-3.jpg" alt="">
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript" src="resources/js/voteDatatables.js"></script>
<script type="text/javascript" src="resources/js/voteUtils.js"></script>
</body>
</html>
