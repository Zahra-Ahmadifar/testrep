
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <link
            rel="stylesheet"
            href="https://cdn.rtlcss.com/bootstrap/v4.2.1/css/bootstrap.min.css"
            integrity="sha384-vus3nQHTD+5mpDiZ4rkEPlnkcyTP+49BhJ4wJeJunw06ZAp+wzzeBPUXr42fi8If"
            crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <style>
        ul.nav a:hover {
            color: #fff !important;
            background-color: #000000;
            background: linear-gradient(
                    rgba(90, 79, 214, 0.72),
                    transparent
            ),
            linear-gradient(
                    90deg,
                    skyblue,
                    transparent
            ),
            linear-gradient(
                    -90deg,
                    #b59bff,
                    transparent
            );
            background-blend-mode: screen;;
            border-radius: 3px;
        }
    </style>

</head>
<body dir="rtl">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark "
     style="border-radius: 7px; margin-left: 10px;margin-right: 10px; margin-top: 12px;">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="nav navbar-nav mr-auto " style="left: 0px ;right: auto;">
                <li class="nav-item">
                    <a class="nav-link" href="#">اطلاعات شخصی<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/managerController.do?action=insertEmployee">افزودن کارمند</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/managerController.do?action=findAll">مدیریت کارمندان</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/managerController.do?action=beneathEmployees">مدیریت مرخصی</a>
                </li>

            </ul>
            <ul class="nav navbar-nav ml-auto " style="left: auto;right: auto;">
                <li><a class="nav-link " href="/managerController.do?action=logout"><span class="fa fa-sign-out"></span>خروج</a></li>
            </ul>
        </div>
    </div>
</nav>
<script
        src="https://cdn.rtlcss.com/bootstrap/v4.2.1/js/bootstrap.min.js"
        integrity="sha384-a9xOd0rz8w0J8zqj1qJic7GPFfyMfoiuDjC9rqXlVOcGO/dmRqzMn34gZYDTel8k"
        crossorigin="anonymous"></script>
</body>
</html>
