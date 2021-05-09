
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.rtlcss.com/bootstrap/v4.2.1/js/bootstrap.min.js"
            integrity="sha384-a9xOd0rz8w0J8zqj1qJic7GPFfyMfoiuDjC9rqXlVOcGO/dmRqzMn34gZYDTel8k"
            crossorigin="anonymous"></script>
    <%--<script src="resources/js/jquery.min.js"></script>--%>
    <%--<script src="resources/js/jquery.validate.min.js"></script>--%>
    <%--<script src="resources/js/input.validation.js"></script>--%>
    <%--<!-- Bootstrap CSS -->--%>
    <link
            rel="stylesheet"
            href="https://cdn.rtlcss.com/bootstrap/v4.2.1/css/bootstrap.min.css"
            integrity="sha384-vus3nQHTD+5mpDiZ4rkEPlnkcyTP+49BhJ4wJeJunw06ZAp+wzzeBPUXr42fi8If"
            crossorigin="anonymous"
    >
    <title>Title</title>
    <jsp:include page="managerHeader.jsp"/>
</head>
<body style="background-color: black" dir="rtl">
<jsp:include page="body.jsp"/>
<c:if test="${sessionScope['invalidUsername']}">

    <div style="width: 300px;border-radius: 5px; margin: 10px auto;">
        <div class="alert alert-success" id="success-alert">
            <button type="button" class="close" data-dismiss="alert">x</button>
            <strong>نام کاربری ناموجود است.
            </strong>
        </div>
    </div>
</c:if>


<div class="container" name="container"
     style="background-color: #777aff; width: 700px; margin-top: 55px;border-radius: 5px;">
    <form action="/managerController.do" method="post" id="saveForm">
        <input type="hidden" name="action" value="save">
        <div class="form-row">
            <div class="form-group col-md-4" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>نام</label>
                <input type="text" class="form-control" id="firstName" name="firstName"
                       placeholder="نام">
            </div>
            <div class="form-group col-md-4" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>نام خانوادگی</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="نام خانوادگی" >
            </div>
            <div class="form-group col-md-4" style="margin-top: 17px;">
                <label><label >*</label>نام پدر</label>
                <input type="text" class="form-control" id="fatherName" name="fatherName" placeholder="نام پدر">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>ایمیل</label>
                <input type="email" name="email" class="form-control" id="email" placeholder="ایمیل">
            </div>
            <div class="form-group col-md-4" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>نام کاربری</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="نام کاربری">
            </div>
            <div class="form-group col-md-4" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>رمز عبور</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="رمز عبور">
            </div>
        </div>


        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>مدیر</label>
                <select name='selectedManager' id="selectedManager" class="form-control">
                    <%--<c:forEach items="<%=request.getAttribute(\"managerList\") %>" var="manager">--%>
                        <%--<option value="<c:out value="${manager}"/>"><c:out value="${manager}"/></option>--%>
                    <%--</c:forEach>--%>
                </select>
            </div>
        </div>

            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><fmt:bundle basename="resource_fa">
                    <fmt:message key="state"/>
                </fmt:bundle>:</label>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="active" name="employeeStatus" value="active"
                           checked>
                    <label class="form-check-label">فعال</label>
                </div>


                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="inactive" name="employeeStatus" value="inactive">
                    <label class="form-check-label">غیرفعال</label>
                </div>
            </div>
        <div class="form-row">
            <div class="form-group col-md-8" style="margin-top: 17px;">
                <label>مقام :</label>

                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="manager" name="post" value="manager" checked>
                    <label class="form-check-label">مدیر</label>
                </div>


                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="programmer" name="post" value="programmer">
                    <label class="form-check-label">برنامه نویس</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="tester" name="post" value="tester">
                    <label class="form-check-label">کارشناس تست</label>
                </div>
            </div>

            <div class=" col-md-4" style="margin-top: 17px;">
                <button type="submit" class="btn btn-primary">ذخیره اطلاعات</button>
            </div>
        </div>
    </form>

</div>

</body>
</html>
