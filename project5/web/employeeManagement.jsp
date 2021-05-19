
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="managerHeader.jsp"/>
    <title>مدیریت کارمندان</title>
</head>
<body dir="rtl">
<jsp:include page="body.jsp"/>
<script>
    function inactiveEmployee(employeeId) {
        if (confirm('کارمند غیرفعال شود؟')) {
            window.location = '/managerController.do?action=inactive&employeeId=' + employeeId;
        }
    }

    function findEmployee(employeeId) {
        window.location = '/managerController.do?action=editEmployee&employeeId=' + employeeId;
    }
</script>

<div class="container" style="margin-top: 50px;border-radius: 6px;background-color: #777aff;">
    <form action="/managerController.do" method="post">
        <div>
            <div class="form-row">
                <input hidden type="hidden" name="action" value="search">
                <div class="form-group col-md-3">
                    <label for="firstName" style="font-weight: 600;">نام</label>
                    <input type="text" name="firstName" class="form-control" id="firstName"
                           value="${requestScope.firstName}"
                           placeholder='نام'>
                </div>
                <div class="form-group col-md-3">
                    <label for="lastName" style="font-weight: 600;">نام خانوادگی</label>
                    <input type="text" name="lastName" class="form-control" id="lastName"
                           value="${requestScope.lastName}"
                           placeholder='نام خانوادگی'>
                </div>


                <div class="form-group col-md-3">
                    <label for="username" style="font-weight: 600;">نام کاربری</label>
                    <input type="text" name="username" class="form-control" id="username"
                           value="${requestScope.username}"
                           placeholder='نام کاربری'>
                </div>
            </div>
            <div>

                <button type="submit" class="btn btn-primary" style="margin-bottom: 10px; "> <span
                        class="fa fa-search">جستجو</span></button>
            </div>

        </div>
    </form>


    <table class="table table-bordered table-hover table-responsive-lg" >
        <thead class="thead-light ">
        <tr style="border-radius: 10px;">
            <th class="text-center" scope="col">نام</th>
            <th class="text-center" scope="col">نام خانوادگی</th>
            <th class="text-center" scope="col">مدیر</th>
            <th class="text-center" scope="col">مقام</th>
            <th class="text-center" scope="col">عملیات</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.employeeList}" var="employee">
            <tr>
                <td hidden><c:out value="${employee.id}"/></td>
                <td><c:out value="${employee.firstName}"/></td>
                <td><c:out value="${employee.lastName}"/></td>
                <td><c:out value="${employee.manager.firstName} ${employee.manager.lastName}"/></td>
                <td><c:out value="${employee.position.name}"/></td>
                <td class="text-right" style="width: 21%;">
                    <button type="button"
                            class="btn btn-primary btn-rounded btn-lm my-0 badge-pill " value="update"
                            style="width: 82px;" onclick="findEmployee(${employee.id})"><span
                            class="fa fa-edit">ویرایش</span></button>
                    <button type="button"
                            class="btn btn-danger btn-rounded btn-lm my-0 badge-pill " value="delete"
                            style="width: 80px;margin-right:10px;" onclick="inactiveEmployee(${employee.id})"><span
                            class="fa fa-trash">حذف</span></button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
