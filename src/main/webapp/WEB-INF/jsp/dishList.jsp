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
            <hr>
            <h2 class="intro-text text-center"><spring:message code="dishes.header"/></h2>
            <hr>
            <div class="col-sm-3 col-md-3">
                <div class="view-box">
                    <div class="form-group">
                        <label class="control-label" for="rest_id"><spring:message code="dishes.rest"></spring:message></label>
                        <select class="form-group" id="rest_id">
                            <c:forEach items="${restNames}" var="rest">
                                <option value="${rest.id}">${rest.name}</option>
                            </c:forEach>
                        </select>
                        <label class="control-label" for="updated"><spring:message code="dishes.date"></spring:message></label>
                        <input type='text' class="form-control" id="updated" />
                        </div>
                        <a class="btn btn-xs btn-primary" onclick="getByDate();"><spring:message code="common.choose"></spring:message></a>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-sm btn-info" onclick="add('dishes.add')"><spring:message code="dishes.add"/></a>
                        <table class="table table-striped display" id="datatable">
                            <thead>
                            <tr>
                                <th><spring:message code="dishes.name"/></th>
                                <th><spring:message code="dishes.price"/></th>
                                <th><spring:message code="dishes.updated"/></th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-offset-6">
                <img class="img-responsive img-full" src="resources/img/slide-3.jpg" alt="">
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">
    <jsp:include page="fragments/i18n.jsp"/>
</script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/dishDatatables.js"></script>
<script type="text/javascript" src="resources/js/dishUtils.js"></script>
</body>
</html>
