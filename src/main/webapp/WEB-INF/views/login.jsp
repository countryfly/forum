<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/7
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@include file="../include/nav.jsp"%>
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-sign-in"></i> 登录</span>
        </div>
        <c:choose>
            <c:when test="${param.state == '1002'}">
                <div class="alert alert-success">你已经安全退出</div>
            </c:when>
        </c:choose>

        <form action="" class="form-horizontal" id="loginForm">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" name="username">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="text" name="password">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">验证码</label>
                <div class="controls">
                    <input type="text" name="patchca">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <a href="javascript:;" id="changePic"><img id="img" src="/patchca.png" alt=""></a>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                    <a href="/forget.do">忘记密码</a>
                </div>
            </div>

            <div class="form-actions">
                <button type="button" class="btn btn-primary" id="subBtn">登录</button>

                <a class="pull-right" href="/reg.do">注册账号</a>
            </div>

        </form>



    </div>
    <!--box end-->
</div>
<!--container end-->

<script src="//cdn.bootcss.com/jquery/1.12.1/jquery.js"></script>
<script src="//cdn.bootcss.com/jquery-validate/1.14.0/jquery.validate.js"></script>
<script>
    $(function(){
        $("#subBtn").click(function(){
            $("#loginForm").submit();
        });
        
        $("#changePic").click(function(){
            $("#img").attr("src","/patchca.png?="+new Date().getTime());
        });
        
        $("#loginForm").validate({
            errorClass:'text-error',
            errorElement:'span',
            rules:{
                username:{
                    required:true
                },
                password:{
                    required:true
                },
                patchca:{
                    required:true,
                    remote:'/validate/patchca.do'
                }
            },
            messages:{
                username:{
                    required:'请输入账号'
                },
                password:{
                    required:'请输入密码'
                },
                patchca:{
                    required:'请输入验证码',
                    remote:'验证码不正确'
                }
            },
            submitHandler:function(form){
                var $btn = $("#subBtn");
                $.ajax({
                    url:"/login.do",
                    type:"post",
                    data:$(form).serialize(),
                    beforeSend:function(){
                        $btn.text("登陆中...").attr("disabled","disabled");
                    },
                    success:function(json){
                        if(json.state == "error"){
                            alert(json.message);
                        }else{
                            window.location.href = "/index.do"
                        }
                    },
                    error:function(){
                        alert("服务器错误");
                    },
                    complete:function(){
                        $btn.text("登录").removeAttr("disabled");
                    }
                });
            }
        });
    });
</script>
</body>
</html>
