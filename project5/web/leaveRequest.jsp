
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>درخواست مرخصی</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script>

        function dateCheck() {
            var leaveFromDate = new Date($('#leaveFromDate').val());
            var leaveToDate = new Date($('#leaveToDate').val());
            if (document.forms["leaveRequestForm"]["leaveFromDate"].value == null || document.forms["leaveRequestForm"]["leaveFromDate"].value === "") {
                window.alert('تاریخ شروع مرخصی را وارد کنید.');
                return false;
            }
            if (document.forms["leaveRequestForm"]["leaveToDate"].value == null || document.forms["leaveRequestForm"]["leaveToDate"].value === "") {
                window.alert('تاریخ پایان مرخصی را وارد کنید.');
                return false;
            }
            if (Date.parse(leaveFromDate) >= Date.parse(leaveToDate)) {
                window.alert('تاریخ پایان مرخصی نباید پیش از تاریخ شروع مرخصی باشد');
                return false;
            }
        }
    </script>

    <jsp:include page="employeeHeader.jsp"/>
</head>
<body dir="rtl">
<jsp:include page="body.jsp"/>
<c:if test="${requestScope['invalidLeaveRequest'] =='invalidLeaveRequest'}">
    <div style="width: 450px;border-radius: 5px; margin: 10px auto;">
        <div class="alert alert-danger" id="danger-alert">
            <button type="button" class="close" data-dismiss="alert">x</button>
            <strong>بازه ی زمانی درخواست شده با درخواست های قبلی در تداخل است.
            </strong>
        </div>
    </div>
</c:if>
<c:if test="${requestScope['invalidLeaveRequest'] =='validLeaveRequest'}">
    <div style="width: 450px;border-radius: 5px; margin: 10px auto;">
        <div class="alert alert-success" id="success-alert">
            <button type="button" class="close" data-dismiss="alert">x</button>
            <strong>درخواست شما با موفقیت ثبت شد.
            </strong>
        </div>
    </div>
</c:if>


<div class="container" name="container"
     style="background-color: #777aff; width: 700px; margin-top: 55px;border-radius: 5px;">
    <form action="/employeeController.do" id="leaveRequestForm" method="post" onsubmit="return dateCheck()">
        <input type="hidden" name="action" value="leaveRequest">
        <div class="form-row">
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>تاریخ شروع مرخصی</label>
                <input type="date" class="form-control" id="leaveFromDate" name="leaveFromDate">
            </div>
            <div class="form-group col-md-6" style="margin-top: 17px;">
                <label><label style="color: #ff4626">*</label>تاریخ پایان مرخصی</label>
                <input type="date" class="form-control" id="leaveToDate" name="leaveToDate">
            </div>
        </div>
        <div class="form-row" dir="rtl">
            <div class="col-md-3" style="margin-top: 17px;margin-bottom: 10px;">
                <button type="submit" class="btn btn-primary">ذخیره مرخصی</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
