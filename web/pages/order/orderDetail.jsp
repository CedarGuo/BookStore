<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Cedar
  Date: 2021/12/5
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>

    <!-- 引入base css jquery-->
    <%@include file="/pages/common/head.jsp"%>



</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单${requestScope.orderId}详情</span>

    <a href="<%=request.getHeader("Referer")%>" >返回</a>

</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>订单号</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
                <td>${orderItem.orderId}</td>
            </tr>
        </c:forEach>

    </table>

</div>
</body>
</html>
