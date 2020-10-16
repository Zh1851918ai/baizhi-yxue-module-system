<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

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
    <link rel="stylesheet" type="text/css" href="${path}/static/bs/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/bs/css/"/>
    <script src="${path}/static/bs/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${path}/static/yxue/yxue.js"></script>
    <style type="text/css">



    </style>
</head>
<body class="container">
<div class="col-md-4 col-md-offset-4">
    <div class="panel" style="width: 300px;">
        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <div>
                <div>

                    <h5 class="logo-name"><img src="${path}/static/img/4.gif"></h5>

                </div>
                <h3>欢迎使用 小知</h3>

                <form class="" role="form" action="${path}/admin/login">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="用户名/手机号/邮箱" required="" name="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="密码" required="" name="password">
                    </div>


                    <div class="form-group">
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号" required="">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="code" name="code" placeholder="请输入验证码" required="">
                        <a id="phoneCode" href="javascript:void(0)" class="btn btn-default">发送验证码</a>
                    </div>

                    <script>
                        $(function () {

                            /**
                             *
                             * @param obj 要控制的标签对象
                             * @param wait  等待的时间
                             */
                            function time(obj,wait) {
                                let $this = $(obj);

                                if (wait==0) {
                                    // 如果等待时间wait为0的时候，设置按钮可以触发
                                    $this.css('pointer-events','');
                                    $this.html('发送验证码');
                                    wait = 60;
                                } else {
                                    // 如果等待时间wait大于0,设置按钮禁用（不可以触发事件）
                                    $this.css('pointer-events','none');
                                    $this.html(wait+'秒后可以重新发送')
                                    // 手动减秒
                                    wait--;
                                    setTimeout(function () {
                                        // 递归调用time函数
                                        time(obj, wait);
                                    }, 1000);
                                }

                            }
                            // 点击发送验证码
                            $('#phoneCode').click(function () {
                                var phoneNum=$('#phone').val();
                                if(phoneNum.length!=11){
                                    alert("重新输入！")
                                }else{
                                    alert("拨打的手机号码为"+phoneNum)
                                }
                                // 控制发送验证码的周期
                                time(this, 60);
                                $.ajax({
                                    url:'${path}/admin/phoneCode?phone='+phoneNum,
                                    type:'post',
                                    dateType:'text',
                                        success: function () {
                                            alert("我发送了手机验证码！QAQ");
                                        }

                                    }

                                )
                                // 获取手机号 发送到后台
                                alert('发送验证码...')
                            })

                        })
                    </script>

                    <h4>${requestScope.message}</h4>
                    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

                    <%--<p class="text-muted text-center"><a href="login.html#">
                        <small>忘记密码了？</small>
                    </a> | <a href="#">注册一个新账号</a>
                    </p>--%>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>