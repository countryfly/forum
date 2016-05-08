<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/7
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@include file="../include/nav.jsp"%>
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title">找回密码</span>
        </div>

        <form  class="form-horizontal" id="forgetForm">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" name="username">
                </div>
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-primary" id="subBtn">提交</button>
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
            $("#forgetForm").submit();
        });
        $("#forgetForm").validate({
            errorClass:'text-error',
            errorElement:'span',
            rules:{
                username:{
                    required:true,
                    remote:'/validate/username.do?action=forget'
                }
            },
            messages:{
                username:{
                    required:'请输入账号',
                    remote:'账号输入不正确'
                }
            }


        });

    });
</script>
</body>
</html>