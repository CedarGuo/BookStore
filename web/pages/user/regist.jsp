<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>
	<!-- 引入base css jquery-->
	<%@include file="/pages/common/head.jsp"%>

	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>

	<script type="text/javascript">


        $(function () {
            //给username绑定失去焦点事件，异步请求，验证用户名是否已存在
            $("#username").blur(function () {
                var username = this.value;
                $.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsUsername&username=" + username,
                    function (data) {
                        if (data.existsUsername) { $("span.errorMsg").text("用户名已存在！");
                        } else {$("span.errorMsg").text("用户名可用！"); }
                    }

                )
            });

            //给注册绑定单击事件，并验证输入信息是否合法
            $("#sub_btn").click (function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //1 获取用户名输入框里的内容
                var usernameText = $("#username").val();

                //2 创建正则表达式对象
				var usernamePatt = /^\w{5,12}$/;

                //3 使用 test 方法验证
				if(!usernamePatt.test(usernameText)){
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名不合法");
                    return false;

                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                var passwordText = $("#password").val();

                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;

                //3 使用 test 方法验证
                if(!passwordPatt.test(passwordText)){
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法");
                    return false;

                }
                // 验证确认密码：和密码相同
                var repwdText = $("#repwd").val();

                //2 创建正则表达式对象

                //3 使用 test 方法验证
                if(repwdText != passwordText){
                    //4 提示用户结果
                    $("span.errorMsg").text("密码输入不一致");
                    return false;

                }
                // 邮箱验证：xxxxx@xxx.com
				var emailText = $("#email").val();
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)){
                    $("span.errorMsg").text("邮箱不合法！");
                    return false;
				}
				
                $("span.errorMsg").text("");

            });

			//给验证图片绑定单击事件，实现验证码切换
            $("#code_img").click(function () {
                this.src= "${basePath}kaptcha.jpg?p=" + new Date();
            });
        })


	</script>
</head>
<body>
<div id="login_header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

	<div id="l_content">
		<span class="login_word">欢迎注册</span>
	</div>

	<div id="content">
		<div class="login_form">
			<div class="login_box">
				<div class="tit">
					<h1>注册尚硅谷会员</h1>

					<a href="pages/user/login.jsp">登录</a>

					<span class="errorMsg"><%= request.getAttribute("msg")==null? "" :request.getAttribute("msg")%></span>
				</div>
				<div class="form">
					<form action="userServlet" method = "post">
						<input type="hidden" name="action" value="regist">
						<label>用户名称：</label>
						<input class="itxt" type="text" placeholder="请输入用户名"
							   autocomplete="off" tabindex="1" name= "username" id="username"
						<%--value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
							   value = "${requestScope.username}"
						/>
						<br />
						<br />
						<label>用户密码：</label>
						<input class="itxt" type="password" placeholder="请输入密码"
							   autocomplete="off" tabindex="1" name="password" id="password"
							   <%--value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"--%>
							   value="${requestScope.password}"
						/>
						<br />
						<br />
						<label>确认密码：</label>
						<input class="itxt" type="password" placeholder="确认密码"
							   autocomplete="off" tabindex="1" name="repwd" id="repwd"
							   <%--value="<%=request.getAttribute("repwd")==null?"":request.getAttribute("repwd")%>"--%>
							   value="${requestScope.repwd}"
						/>
						<br />
						<br />
						<label>电子邮件：</label>
						<input class="itxt" type="text" placeholder="请输入邮箱地址"
							   autocomplete="off" tabindex="1" name="email" id="email"
							   <%--value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>
							   value="${requestScope.email}"
						/>
						<br />
						<br />
						<label>验证码：</label>
						<input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>
						<img alt="" src="kaptcha.jpg" style="float: right; width: 100px; height: 35px;margin-right: 20px" id="code_img">
						<br />
						<br />
						<input type="submit" value="注册" id="sub_btn" />

					</form>
				</div>

			</div>
		</div>
	</div>
</div>
<%--页脚部分--%>
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>