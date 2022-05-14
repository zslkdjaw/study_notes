<%--
  Created by IntelliJ IDEA.
  User: dooo
  Date: 2022/5/8
  Time: 13:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>订单信息系统</title>

</head>
<body>

<head>
    <table width="100%" height="700"  cellpadding="0" cellspacing="0" border="1">
        <tr>
            <h2 align="center">订单信息系统</h2>
        </tr>
        <tr >
            <td width="15%" height="100%" valign="top" >
                <a href="${pageContext.request.contextPath}/listClient" target="mainFrame" style="padding-left: 90px">查询客户</a><br>
                <a href="${pageContext.request.contextPath}/listProduct" target="mainFrame"style="padding-left: 90px">查询产品</a><br>
                <a href="${pageContext.request.contextPath}/listOrder" target="mainFrame"style="padding-left: 90px">查询订单</a><br>
            </td>
            <td width="60%" height="100%" valign="top" >
                <iframe name="mainFrame" width="1100" height="2000" scrolling="no" frameborder="0" align="center"></iframe>
            </td>
        </tr>
    </table>
</body>
</html>
