<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>编辑图书</title>
	<!-- 引入base css jquery-->
	<%@include file="/pages/common/head.jsp"%>

	<style type="text/css">
		h1 {
			text-align: center;
			margin-top: 200px;
		}

		h1 a {
			color:red;
		}

		input {
			text-align: center;
		}
	</style>

	<script type="text/javascript">
        $(function () {
            $("#submit").click(function () {
                <%--return confirm("确定要修改《" + ${requestScope.book.name} + "》吗?")--%>
                if (document.getElementById("requestMethod").value == "update"){

                    return confirm("确定要修改《" + $(this).parent().parent().find("td:first").find("input").val() + "》吗")
                }
                if (document.getElementById("requestMethod").value == "add"){
                    return confirm("确定要添加《" + $(this).parent().parent().find("td:first").find("input").val() + "》吗")
                }
            })
        })
	</script>

</head>
<body>
<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">编辑图书</span>
	<%--管理页面的链接--%>
	<%@include file="/pages/common/manager_menu.jsp"%>
</div>

<div id="main">
	<form action="manager/bookServlet" method="get">
		<%--动态修改隐藏域：在book_manager.jsp的a标签处添加method参数，
		当参数method="add"时实现添加，=“update”时实现修改操作--%>
		<%--param是el表达式的隐含对象，可以获得请求参数的值--%>
		<input type="hidden" id="requestMethod" name="action" value="${param.method}">
		<input type="hidden" name="pageNo" value="${param.pageNo}">
		<input type="hidden" name="id" value="${requestScope.book.id}">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<tr>
				<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
				<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
				<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
				<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
				<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
				<td><input type="submit" value="提交" id="submit"/></td>
			</tr>
		</table>
	</form>


</div>

<%--页脚部分--%>
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>