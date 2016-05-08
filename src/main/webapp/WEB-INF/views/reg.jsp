<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/7
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册用户</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@include file="../include/nav.jsp"%>
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-sign-in"></i> 注册账号</span>
        </div>

        <form action="" class="form-horizontal" id="regForm">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" name="username">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="text" name="password" id="password">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码</label>
                <div class="controls">
                    <input type="text" name="repassword">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">电子邮件</label>
                <div class="controls">
                    <input type="text" name="email">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">验证码</label>
                <div class="controls">
                    <input type="text" name="patchca">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                    <a href="javascript:;"id="changePic"><img id="img" src="/patchca.png" alt=""></a>
                </div>
            </div>
            <div class="form-actions">
                <button type="button" id="subBtn" class="btn btn-primary">注册</button>
                <span id="regMsg" class="hide">注册成功,<span class="sec">3</span>秒后自动跳转到登录页面</span>
                <a class="pull-right" href="/login.do">登录</a>
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
            $("#regForm").submit();
        });

        $("#changePic").click(function(){
            $("#img").attr("src","/patchca.png?="+new Date().getTime());
        });

        $("#regForm").validate({
            errorClass:'text-error',
            errorElement:'span',
            rules:{
                username:{
                    required:true,
                    rangelength:[3,10],
                    remote:"/validate/username.do"

                },
                password:{
                    required:true,
                    rangelength:[6,10]
                },
                repassword:{
                    required:true,
                    rangelength:[6,10],
                    equalTo:'#password'
                },
                email:{
                    required:true,
                    email:true,
                    remote:"/validate/email.do"
                },
                patchca:{
                    required:true,
                    remote:"/validate/patchca.do"
                }
            },
            messages:{
                username:{
                    required:'请输入账号',
                    rangelength:'账号长度3~10位',
                    remote:'该账号已被占用'
                },
                password:{
                    required:'请输入密码',
                    rangelength:'密码长度6~10位'
                },
                repassword:{
                    required:'请重复密码',
                    rangelength:'重复密码长度6~10位',
                    equalTo:'两次输入密码不一致'
                },
                email:{
                    required:'请输入电子邮箱',
                    email:'请输入正确的邮箱格式',
                    remote:'邮箱已被注册'
                },
                patchca:{
                    required:'请输入验证码',
                    remote:'验证码输入错误'
                }
            },
            submitHandler:function(form){
                $.post("/reg.do",$(form).serialize()).done(function(result){
                    if(result.state == "error"){
                        alert(result.message);
                    }else{
                        $("#regMsg").removeClass("hide");
                        var sec = 3;
                        setInterval(function(){
                            sec--;
                            if(sec == 0){
                                window.location.href = "/login.do";
                                return;
                            }
                            $("#regMsg .sec").text(sec);
                        },1000);
                    }
                }).fail(function(){
                    alert("服务器错误");
                });
            }
        });



    });
</script>
</body>
</html>
