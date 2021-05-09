
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <title>ویرایش کارمند</title>
    <jsp:include page="managerHeader.jsp"/>
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/jquery.validate.min.js"></script>
    <script src="resources/js/input.validation.js"></script>
</head>
<body dir="rtl">
<jsp:include page="body.jsp"/>

<div class="container" name="container"
     style="background-color: #777aff; width: 700px; margin-top: 55px;border-radius: 5px;">
    <form action="/managerController.do" method="post" id="editForm">
        <input type="hidden" name="action" value="update">
        <input hidden type="hidden" name="id" value="${requestScope.Employee.id}">
        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label>نام کاربری</label>
                <input type="text" readonly class="form-control" id="username" name="username"
                       value="<c:out value= '${requestScope.Employee.username}'/>">
            </div>
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label>رمز عبور </label>
                <input type="text" readonly class="form-control" id="password" name="password"
                       value="<c:out value= '${requestScope.Employee.password}'/>">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>نام</label>
                <input type="text" class="form-control" id="firstName" name="firstName"
                       value="<c:out value='${requestScope.Employee.firstName}'/>">
            </div>
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label style="color: #ff4626">*</label><label>نام خانوادگی</label>
                <input type="text" name="lastName" class="form-control" id="lastName"
                       value="<c:out value='${requestScope.Employee.lastName}'/>">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label>نام پدر</label>
                <input type="text" class="form-control" id="fatherName" name="fatherName"
                       value="<c:out value='${requestScope.Employee.fatherName}'/>">
            </div>
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>ایمیل</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="<c:out value='${requestScope.Employee.email}'/>">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-5" style="margin-top: 17px;">
                <label>مدیر</label>
                <select name='selectedManager' id="selectedManager" class="form-control">
                    <c:forEach items="<%=request.getAttribute(\"managerList\") %>" var="manager">
                        <option value="<c:out value="${manager}"/>" ${manager.equals(lastManager) ? 'selected="selected"' : ''} >
                            <c:out value="${manager}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-7" style="margin-top: 17px;">
                <br/>
                <label>وضعیت : </label>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="active" name="employeeStatus"
                           value="active" ${requestScope.Employee.employeeStatus.code=='active'?'checked':''} >
                    <label class="form-check-label">فعال</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="inactive" name="employeeStatus"
                           value="inactive" ${requestScope.Employee.employeeStatus.code=='inactive'?'checked':''} >
                    <label class="form-check-label">غیرفعال</label>
                </div>
            </div>
        </div>
        <div class="form-row" dir="ltr">
            <div class=" col-md-3" style="margin-top: 17px;margin-bottom: 10px;">

                <button type="submit" class="btn btn-primary" style="width:90px;">ویرایش </button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
