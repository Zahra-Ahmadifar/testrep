
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>لیست پیام ها</title>
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
<script>
    function downloadFile(fileName) {
        window.location = '/employeeController.do?action=downloadFile&fileName=' + fileName;
    }
</script>
<c:choose>
<c:when test="${empty requestScope.receivedEmailsInfo}">
    <div style="width: 400px;border-radius: 5px; margin: 10px auto;text-align: center;">
        <div class="alert alert-info" id="info-alert">
            <button type="button" class="close" data-dismiss="alert">x</button>
            <strong>پیامی برای شما ارسال نشده است.</strong>
        </div>
    </div>
</c:when>
<c:otherwise>
<div class="container" style="margin-top: 50px;border-radius: 6px;background-color: #777aff;">
    <span style="font-weight: bold;font-size: 17px;color:#04008a"><h>لیست پیام های دریافت شده:</h></span>
    <table class="table table-bordered table-hover table-responsive-lg">
        <thead class="thead-light ">
        <tr style="border-radius: 10px;">
            <th class="text-center" scope="col">تاریخ دریافت</th>
            <th class="text-center" scope="col">نام ارسال کننذه</th>
            <th class="text-center" scope="col">نام خانوادگی ارسال کننده</th>
            <th class="text-center" scope="col">موضوع پیام</th>
            <th class="text-center" scope="col">محتوا پیام</th>
            <th class="text-center" scope="col">فایل پیوست</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.receivedEmailsInfo}" var="receivedEmails">
            <tr>
                <td hidden><c:out value="${receivedEmails[0]}"/></td>
                <td><c:out value="${receivedEmails[1]}"/></td>
                <td><c:out value="${receivedEmails[2]}"/></td>
                <td><c:out value="${receivedEmails[3]}"/></td>
                <td><c:out value="${receivedEmails[4]}"/></td>
                <td><c:out value="${receivedEmails[5]}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${empty receivedEmails[5]}">
                            <span>فایلی موجود نیست.</span>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-primary btn-lm my-0 badge-pill"
                                    style="width: 110px; border-radius: 7px; align-self: center;"
                                    onclick="downloadFile('${receivedEmails[6]}')">دریافت فایل
                            </button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:otherwise>
    </c:choose>
</div>


<c:choose>
<c:when test="${empty requestScope.sentEmailsInfo}">
    <div style="width: 400px;border-radius: 5px; margin: 10px auto;text-align: center;">
        <div class="alert alert-info">
            <button type="button" class="close" data-dismiss="alert">x</button>
            <strong>تا کنون پیامی ارسال نکرده اید.
            </strong>
        </div>
    </div>
</c:when>
<c:otherwise>
<div class="container" style="margin-top: 50px;border-radius: 6px;background-color: #777aff;">
    <span style="font-weight: bold;font-size: 17px;color:#04008a"><h>لیست پیام های ارسال شده:</h></span>
    <table class="table table-bordered table-hover table-responsive-lg">
        <thead class="thead-light ">
        <tr style="border-radius: 10px;">
            <th class="text-center" scope="col">تاریخ ارسال</th>
            <th class="text-center" scope="col">نام دریافت کننده</th>
            <th class="text-center" scope="col">نام خانوادگی دریافت کننده</th>
            <th class="text-center" scope="col">موضوع پیام</th>
            <th class="text-center" scope="col">محتوا پیام</th>
            <th class="text-center" scope="col">فایل پیوست</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.sentEmailsInfo}" var="sentEmails">
            <tr>
                <td hidden><c:out value="${sentEmails[0]}"/></td>
                <td><c:out value="${sentEmails[1]}"/></td>
                <td><c:out value="${sentEmails[2]}"/></td>
                <td><c:out value="${sentEmails[3]}"/></td>
                <td><c:out value="${sentEmails[4]}"/></td>
                <td><c:out value="${sentEmails[5]}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${empty sentEmails[6]}">
                            <span><fmt:bundle basename="resource_fa">
                                <fmt:message key="no attachment file"/>
                            </fmt:bundle></span>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-primary btn-lm my-0 badge-pill"
                                    style="width: 110px; border-radius: 7px; align-self: center;"
                                    onclick="downloadFile('${sentEmails[5]}')">دریافت فایل
                            </button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:otherwise>
    </c:choose>
</div>


</body>
</html>