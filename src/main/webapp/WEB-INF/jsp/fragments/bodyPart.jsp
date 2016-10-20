<%--
  Created by IntelliJ IDEA.
  User: fox
  Date: 30.08.16
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="brand"><spring:message code="app.title"/></div>

<!-- Navigation -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                <li>
                    <a href="/voting"><spring:message code="voting.title"/> </a>
                </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li>
                        <a href="/users"><spring:message code="users.title"/></a>
                    </li>
                    <li>
                        <a href="/restaurants"><spring:message code="restaurants.title"/></a>
                    </li>
                    <li>
                        <a href="/dishes"><spring:message code="dishes.title"/></a>
                    </li>
                    </sec:authorize>
                <li>
                    <a href="/profile"><spring:message code="profile.title"/></a>
                </li>
                <li>
                    <a href="/logout"><spring:message code="app.logout"/></a>
                </li>
                </sec:authorize>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
