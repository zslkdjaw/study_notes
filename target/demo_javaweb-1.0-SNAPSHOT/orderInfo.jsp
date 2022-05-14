<%--
  Created by IntelliJ IDEA.
  User: dooo
  Date: 2022/5/14
  Time: 17:01
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
    <style type="text/css">
        table td{
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h2 align="center">订单${param.orderId}明细表</h2>

<table align="center">
    <tr bgcolor="#949494">
        <th>产品名</th><th>产品ID</th><th>订购数量</th><th>产品单价/元</th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td align="center">${p.productName}</td>
            <td align="center">${p.productId}</td>
            <td align="center">${p.quantity}</td>
            <td align="center">${p.price}</td>
        </tr>
    </c:forEach>
    <tr>
        <td style="border: none" align="center"></td>
        <td style="border: none" align="center"></td>
        <td style="border: none" align="center"></td>
        <td style="border: none" align="center">共需支付${param.payment}元</td>
    </tr>
</table>
</body>
</html>
