<%--
  Created by IntelliJ IDEA.
  User: fox
  Date: 30.08.16
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <p class="text-center">
                    <spring:message code="app.footer"/>
                </p>
            </div>
            <div class="col-lg-4">
                <p class="text-center">
                    <select class="btn-xs">
                        <option disabled selected value> <spring:message code="common.lang"/> </option>
                        <option onclick="show('en')">English</option>
                        <option onclick="show('ru')">Русский</option>
                    </select>
                </p>
            </div>
        </div>
    </div>
</footer>

<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="webjars/datetimepicker/2.4.7/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="resources/js/pageNotes.js"></script>