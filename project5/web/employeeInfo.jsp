
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>اطلاعات شخصی</title>
    <jsp:include page="employeeHeader.jsp"/>
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/jquery.validate.min.js"></script>
    <script src="resources/js/input.validation.js"></script>
</head>
<body dir="rtl">
<jsp:include page="body.jsp"/>
<div class="container" name="container"
     style="background-color: rgb(119,122,255); width: 700px; margin-top: 55px;border-radius: 5px;">
    <form action="/employeeController.do" method="post" id="editForm">
        <input type="hidden" name="action" value="update">
        <input hidden type="hidden" name="id" value="${requestScope.Employee.id}">
        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label>نام کاربری</label>
                <input type="text" readonly class="form-control" id="username" name="username"
                       value="${requestScope.Employee.username}">
            </div>
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label>رمز عبور</label>
                <input type="text" readonly class="form-control" id="password" name="password"
                       value="${requestScope.Employee.password}">
            </div>
        </div>
        <div class="form-row">

            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>نام</label>
                <input type="text" class="form-control" id="firstName" name="firstName"
                       value="${requestScope.Employee.firstName}">
            </div>

            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>نام خانوادگی</label>
                <input type="text" name="lastName" class="form-control" id="lastName"
                       value="${requestScope.Employee.lastName}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label>*</label>نام پدر</label>
                <input type="text" class="form-control" id="fatherName" name="fatherName"
                       value="${requestScope.Employee.fatherName}">
            </div>
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>ایمیل</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${requestScope.Employee.email}">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label>مدیر</label>
                <input type="text" readonly class="form-control" id="manager" name="manager"
                       value="${requestScope.Employee.manager.firstName} ${requestScope.Employee.manager.lastName}"/>
            </div>

        </div>
        <div class="form-row" dir="ltr">
            <div class=" col-md-3" style="margin-top: 17px;margin-bottom: 10px;">
                <button type="submit" class="btn btn-primary" style="width:90px;" >ویرایش</button>
            </div>
        </div>
    </form>

</div>
</body>
</html>
