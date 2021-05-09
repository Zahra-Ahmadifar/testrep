
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html dir="rtl">
<head>
    <link href="/resources/css/login.css" rel="stylesheet"/>
    <script type="text/javascript">

        function validateForm() {
            var username = document.forms["Form"]["username"].value;
            var password = document.forms["Form"]["password"].value;
            if (username == null || username == "" ) {
                window.alert('لطفا نام کاربری را وارد کنید');
                return false;
            }if(password == null || password == ""){
                window.alert('لطفا رمزعبور را وارد کنید.');
                return false;
            }
        }
    </script>
</head>

<body style="background-color:#b88abc">

<h2>ورود به سامانه</h2><br>

<if test= "${requestScope['invalidUser'] && empty sessionScope['username']}">
    <div>
        <label id="" style=" vertical-align: middle;
    display:inline-block;
    width:100%;
    text-align:center;
    height:30px;
    line-height:30px;
    color: #ff2212;">
            <b>نام کاربری یا رمزعبور نامعتبر است.
            </b>
        </label>
    </div>
</if>


<div class="login">

    <form id="login" name="Form" action="/loginController.do" method="post" onsubmit="return validateForm()">
        <label><b>نام کاربری
        </b>
        </label>
        <input type="text" name="username" id="Uname" placeholder="Username">
        <br><br>
        <label><b>رمز عبور
        </b>
        </label>
        <input type="Password" name="password" id="Pass" placeholder="Password">
        <br><br>
        <button type="submit" name="log" id="log">ورود</button>
        <br><br>

    </form>
</div>

</body>
</html>

