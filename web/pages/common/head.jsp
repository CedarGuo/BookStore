<%--
  Created by IntelliJ IDEA.
  User: Cedar
  Date: 2021/11/28
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String basePath = request.getScheme()
        + "://"
        + request.getServerName()
        + ":"
        + request.getServerPort()
        + request.getContextPath() + "/";
%>

<!--写 base 标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<!-- 引入css文件-->
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<!-- 引入jqurey文件-->
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
