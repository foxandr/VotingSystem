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

<<div class="container">
    <div class="box">
        <div class="row2">
            <hr>
            <h2 class="intro-text text-center"><spring:message code="restaurants.header"/></h2>
            <hr>
            <div class="col-sm-3 col-md-3">
                <div class="view-box">
                    <a class="btn btn-sm btn-info" onclick="add('restaurants.add')"><spring:message code="restaurants.add"/></a>
                    <table class="table table-striped display" id="datatable">
                        <thead>
                        <tr>
                            <th><spring:message code="restaurants.name"/></th>
                            <th><spring:message code="restaurants.address"/></th>
                            <th><spring:message code="users.registered"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-offset-6">
                <img class="img-responsive img-full" src="resources/img/slide-2.jpg" alt="">
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">
    <jsp:include page="fragments/i18n.jsp"/>
</script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/restaurantDatatables.js"></script>
</body>
</html>
