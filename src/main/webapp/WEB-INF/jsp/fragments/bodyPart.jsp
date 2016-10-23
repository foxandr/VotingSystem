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

<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                <li>
                    <a href="/voting"><h5><spring:message code="voting.title"/></h5></a>
                </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li>
                        <a href="/users"><h5><spring:message code="users.title"/></h5></a>
                    </li>
                    <li>
                        <a href="/restaurants"><h5><spring:message code="restaurants.title"/></h5></a>
                    </li>
                    <li>
                        <a href="/dishes"><h5><spring:message code="dishes.title"/></h5></a>
                    </li>
                    </sec:authorize>
                <li>
                    <a href="/profile"><h5><spring:message code="profile.title"/></h5></a>
                </li>
                <li>
                    <a href="/logout"><h5><spring:message code="app.logout"/></h5></a>
                </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
