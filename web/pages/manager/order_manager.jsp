<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<!-- 引入base css jquery-->
	<%@include file="/pages/common/head.jsp"%>



</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%--管理页面的链接--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<%--0 未发货，1 已发货，2 表示已签收--%>
					<td>
						<c:if test="${order.status==0}">
							<a href="orderServlet?action=sendOrder&orderId=${order.orderId}&status=1">点击发货</a>
						</c:if>
						<c:if test="${order.status == 1}">
							已发货
						</c:if>
						<c:if test="${order.status == 2}">
							对方已签收，订单完成
						</c:if>
					</td>
				</tr>
			</c:forEach>


		</table>
	</div>

	<%--页脚部分--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>