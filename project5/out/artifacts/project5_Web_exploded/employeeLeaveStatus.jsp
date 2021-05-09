<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link
            rel="stylesheet"
            href="https://cdn.rtlcss.com/bootstrap/v4.2.1/css/bootstrap.min.css"
            integrity="sha384-vus3nQHTD+5mpDiZ4rkEPlnkcyTP+49BhJ4wJeJunw06ZAp+wzzeBPUXr42fi8If"
            crossorigin="anonymous">
    <jsp:include page="employeeHeader.jsp"/>
</head>
<body dir="rtl">
<jsp:include page="body.jsp"/>
<c:choose>
<c:when test="${empty requestScope.leaveEmployeeList}">
    <div style="width: 400px;border-radius: 5px; margin: 10px auto;text-align: center;">
        <div class="alert alert-info" id="info-alert">
            <button type="button" class="close" data-dismiss="alert">x</button>
            <strong>درخواستی وجود ندارد
            </strong>
        </div>
    </div>
</c:when>
<c:otherwise>
<div class="container" style="margin-top: 50px;border-radius: 6px;background-color: #777aff;">
    <table class="table table-bordered table-hover table-responsive-lg">
        <thead class="thead-light ">
        <tr style="border-radius: 10px;">
            <th class="text-center" scope="col">تاریخ شروع</th>
            <th class="text-center" scope="col">تاریخ پایان</th>
            <th class="text-center" scope="col">وضعیت مرخصی</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.leaveEmployeeList}" var="leaveEmployee">
            <tr>
                <td hidden><c:out value="${leaveEmployee.id}"/></td>
                <td><c:out value="${leaveEmployee.leaveFromDate}"/></td>
                <td><c:out value="${leaveEmployee.leaveToDate}"/></td>
                <td><c:out value="${leaveEmployee.leaveStatus.name}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:otherwise>
    </c:choose>
</div>
</body>
</html>
