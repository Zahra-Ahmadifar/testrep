
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
    <jsp:include page="managerHeader.jsp"/>
    <script>
        function acceptLeave(leaveId) {
            window.location = '/managerController.do?action=acceptLeave&leaveId=' + leaveId;

        }

        function rejectLeave(leaveId) {
            if (confirm('درخواست مرخصی رد شود؟')) {
                window.location = '/managerController.do?action=rejectLeave&leaveId=' + leaveId;
            }
        }
    </script>
</head>
<body dir="rtl">
<jsp:include page="body.jsp"/>
<c:choose>
<c:when test="${empty requestScope.beneathEmployee}">
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
            <th class="text-center" scope="col">نام</th>
            <th class="text-center" scope="col">نام خانوادگی</th>
            <th class="text-center" scope="col">تاریخ شروع مرخصی</th>
            <th class="text-center" scope="col">تاریخ پایان مرخصی</th>
            <th class="text-center" scope="col">وضیت مرخصی</th>
            <th class="text-center" scope="col">عملیات</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.beneathEmployee}" var="Employee">
            <c:forEach items="${Employee.leaveList}" var="leave">
                <tr>
                    <td hidden><c:out value="${leave.id}"/></td>
                    <td><c:out value="${Employee.firstName}"/></td>
                    <td><c:out value="${Employee.lastName}"/></td>
                    <td><c:out value="${leave.leaveFromDate}"/></td>
                    <td><c:out value="${leave.leaveToDate}"/></td>
                    <td><c:out value="${leave.leaveStatus.name}"/></td>
                    <td class="text-right" style="width: 21%;">
                        <button type="button"
                                class="btn btn-primary btn-rounded btn-lm my-0 badge-pill " value="update"
                                style="width: 82px;background-color: #21b900" onclick="acceptLeave(${leave.id})"><span
                                class="fa fa-check"> تایید</span></button>
                        <button type="button"
                                class="btn btn-danger btn-rounded btn-lm my-0 badge-pill " value="delete"
                                style="width: 80px;margin-right:10px; background-color: #ff1703" onclick="rejectLeave(${leave.id})"><span
                                class="fa fa-times">رد </span></button>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
    </c:otherwise>
    </c:choose>
</div>
</body>
</html>
