<%--
  Created by IntelliJ IDEA.
  User: dooo
  Date: 2022/5/14
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%request.setCharacterEncoding("utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html; charset = UTF-8" pageEncoding="UTF-8" %>
<%--el表达式的开启--%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">订单表</h2>
<table border="1" align="center">
    <tr bgcolor="#949494">
        <th>客户名</th><th>客户ID</th><th>订单ID</th><th>订单日期</th><th>订单金额</th><th>订单详情</th>
    </tr>
    <c:forEach items="${orders}" var="o">
        <tr>
            <td align="center">${o.clientName}</td>
            <td align="center">${o.clientId}</td>
            <td align="center">${o.orderId}</td>
            <td align="center">${o.orderDate}</td>
            <td align="center">${o.payment}</td>
            <td align="center"><a href="${pageContext.request.contextPath}/listProducts?orderId=${o.orderId}&payment=${o.payment}">订单详情</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
