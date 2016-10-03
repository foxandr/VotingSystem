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
                        <label class="control-label" for="results"><spring:message code="voting.results"></spring:message></label>
                        <table class="table table-stripped display" id="results">
                            <thead>
                            <tr>
                                <th><spring:message code="voting.rank"></spring:message></th>
                                <th><spring:message code="restaurants.name"></spring:message></th>
                                <th><spring:message code="voting.numbervotes"></spring:message></th>
                            </tr>
                            <c:set var="count" value="1"/>
                            <c:forEach items="${votingResults}" var="entry">
                                <tr>
                                    <td>
                                        ${count}
                                    </td>
                                    <td>
                                        ${restNames.get(entry.key)}
                                    </td>
                                    <td>
                                        ${entry.value}
                                    </td>
                                </tr>
                                <c:set var="count" value="${count + 1}"/>
                            </c:forEach>
                            </thead>
                        </table>
                    </div>
                    <%--<a class="btn btn-xs btn-primary" onclick="getByDate();"><spring:message code="common.choose"></spring:message></a>--%>
                </div>
                <%--<div class="form-group">--%>
                    <%--<a class="btn btn-sm btn-info" onclick="addDish('dishes.add')"><spring:message code="dishes.add"/></a>--%>
                    <%--<table class="table table-striped display" id="datatable">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th><spring:message code="dishes.name"/></th>--%>
                            <%--<th><spring:message code="dishes.price"/></th>--%>
                            <%--<th><spring:message code="dishes.updated"/></th>--%>
                            <%--<th></th>--%>
                            <%--<th></th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                    <%--</table>--%>
                <%--</div>--%>
            </div>
        </div>
        <div class="col-sm-3 col-md-3 col-lg-offset-6">
            <img class="img-responsive img-full" src="resources/img/slide-1.jpg" alt="">
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
