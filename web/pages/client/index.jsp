<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>书城首页</title>

	<!-- 引入base css jquery-->
	<%@include file="/pages/common/head.jsp"%>

	<%--搜索指定页数--%>
	<script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();

                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });

		$(function () {
		    $(".addToCart").click(function () {
                var bookId = $(this).attr("bookId");
				$.getJSON("${pageScope.basePath}cartServlet?action=ajaxAddItem&id="+bookId,function (data) {
                    $("#cartTotalCount").text("您的购物车中有 " + data.totalCount + " 件商品");
                    $(".cartLastName").text(data.lastName);
                });
            });

            <%--$(".addToCart").click(function () {--%>
				<%--var bookId = $(this).attr("bookId")--%>
				<%--location.href = "${pageScope.basePath}cartServlet?action=addItem&id="+bookId;--%>
            <%--});--%>
        });
	</script>



</head>
<body>
<%--商品框顶部功能键--%>
<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">网上书城</span>
	<div>
		<c:if test="${empty sessionScope.user}">
			<a href="pages/user/login.jsp">登录</a> |
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
		</c:if>
		<c:if test="${not empty sessionScope.user}">
			<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
			<a href="orderServlet?action=showMyOrders">我的订单</a>
			<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
		</c:if>
		<a href="pages/cart/cart.jsp">购物车</a>
		<a href="pages/manager/manager.jsp">后台管理</a>
	</div>
</div>

<%--商品部分--%>
<div id="main">
	<div id="book">
		<div class="book_cond">
			<form action="client/clientBookServlet" method="get">
				<input type="hidden" id="requestMethod" name="action" value="pageByPrice">
				价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
				<input id="max" type="text" name="max" value="${param.max}"> 元
				<input type="submit" value="查询" />
			</form>
		</div>

		<div style="text-align: center">
			<c:if test="${empty sessionScope.cart.items}">
				<%--购物车为空的输出--%>
				<span id="cartTotalCount"> </span>
				<div>
					<span style="color: red" class="cartLastName">当前购物车为空</span>
				</div>
			</c:if>
			<c:if test="${ not empty sessionScope.cart.items}">
				<%--购物车非空的输出--%>
				<span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
				<div>
					您刚刚将<span style="color: red" class="cartLastName">${sessionScope.lastName}</span>加入到了购物车中
				</div>
			</c:if>

		</div>

		<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="static/img/default.jpg" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${book.id}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>



</div>

<%--分页条--%>
<%@include file="/pages/common/page.jsp"%>


<%--页脚部分--%>
<%@include file="/pages/common/foot.jsp"%>

</body>
</html>