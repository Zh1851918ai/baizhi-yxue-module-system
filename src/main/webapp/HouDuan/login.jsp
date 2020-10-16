<%--
  Created by IntelliJ IDEA.
  User: 510教师机
  Date: 2020/9/27
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <!--在手机网站，都需要加上视口约束！！！-->
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
    <!--以最新的内核渲染页面-->
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>登录</title>
    <!--引入css文件-->
    <link rel="stylesheet" type="text/css" href="/yxue/static/bs/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/yxue/static/bs/css/"/>
    <script src="/yxue/static/bs/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/yxue/static/yxue/yxue.js"></script>
    <style type="text/css">


    </style>
</head>
<body class="container">
<div class="col-md-4 col-md-offset-4">
    <div class="panel" style="width: 300px;">
        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <div>
                <div>

                    <h1 class="logo-name"><img src="img/logo.png"></h1>

                </div>
                <h3>欢迎使用 小知</h3>

                <form class="" role="form" action="main.html">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="用户名/手机号/邮箱" required="">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="密码" required="">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号" required="">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="code" name="code" placeholder="请输入验证码" required="">
                        <a id="sendPhoneCode" href="javascript:void(0)" class="btn btn-default">发送验证码</a>
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
                    <script>
                        $(function () {

                            // 点击发送验证码


                        })
                    </script>

                    <p class="text-muted text-center"><a href="login.html#">
                        <small>忘记密码了？</small>
                    </a> | <a href="#">注册一个新账号</a>
                    </p>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>