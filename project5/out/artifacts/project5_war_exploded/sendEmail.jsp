
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html dir="rtl">
<head>
    <title>ارسال ایمیل</title>
    <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
          integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/i9multiselect.css"/>
    <link
            rel="stylesheet"
            href="https://cdn.rtlcss.com/bootstrap/v4.2.1/css/bootstrap.min.css"
            integrity="sha384-vus3nQHTD+5mpDiZ4rkEPlnkcyTP+49BhJ4wJeJunw06ZAp+wzzeBPUXr42fi8If"
            crossorigin="anonymous">
    <style>
        select option {
            padding: 5px;
        }
    </style>


    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/i9multiselect.js"></script>
    <script src="https://cdn.rtlcss.com/bootstrap/v4.2.1/js/bootstrap.min.js"
            integrity="sha384-a9xOd0rz8w0J8zqj1qJic7GPFfyMfoiuDjC9rqXlVOcGO/dmRqzMn34gZYDTel8k"
            crossorigin="anonymous"></script>
    <script>
        $(() => {
            $('select').i9multiselect();
        });
    </script>
    <script>
        function validation() {
            var select = document.forms["form"]["select"].value;
            var message = document.forms["form"]["form_message"].value;
            if(select === "" || select == null){
                window.alert('دریافت کنندگان پیام را مشخص کنید.');
                return false;
            }
            if(message ==="" || message ==null){
                window.alert('پیام را وارد کنید.');
                return false;
            }
        }
    </script>
    <meta charset="utf-8">
    <jsp:include page="employeeHeader.jsp"/>
</head>
<body>

<div class="container" style="background-color:#777aff;border-radius: 5px;margin: 50px auto; width: 700px;">
    <jsp:include page="body.jsp"/>
    <form id="form" action="/employeeController.do" method="post" enctype="multipart/form-data" onsubmit="return validation()">
        <input type="hidden" name="action" value="dispatchEmail">
        <div class="form-row">
            <div class="form-group col-md-12 " style="margin-top: 17px;">
                <label for="select">ارسال به:</label>
                <select multiple size="3" name="select" id="select"
                        class="form-control custom-select multiselect">
                    <c:forEach items="<%=request.getAttribute("employeeList") %>" var="employee">
                        <option value="<c:out value= '${employee.id}'/>">&nbsp;&nbsp;&nbsp;<c:out
                                value="${employee.firstName} ${employee.lastName}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-12">
                <label for="form_message">موضوع:</label>
                <textarea id="subject" name="subject" class="form-control"  rows="1"></textarea>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="form_message"><label style="color: #ff4626">* </label>پیام:</label>
                <textarea id="form_message" name="message" class="form-control"  rows="7"></textarea>
                <div class="help-block with-errors"></div>
            </div>
            <div class="form-row">
                <div  class="form-group col-md-7" style="margin-bottom: 12px;">
                    <button type="submit" class="btn btn-primary btn-send">ارسال ایمیل</button>
                </div>
                <div class="form-group col-md-3" style="margin-bottom: 12px;">
                    <label >آپلود فایل</label>
                </div>
                <div class="form-group col-md-2" style="margin-bottom: 12px;">
                    <input type="file" name="file" id="file" size="50"/>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>