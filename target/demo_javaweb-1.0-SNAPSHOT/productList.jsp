<%--
  Created by IntelliJ IDEA.
  User: dooo
  Date: 2022/5/12
  Time: 12:42
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
<h2 align="center">产品表</h2>
<form  name="form2">
    <table>
        <tr >
            <td>
                <input type="text" name="productId" placeholder="产品ID" >
            </td>
            <td>
                <input type="text" name="productName" placeholder="产品名">
            </td>
            <td>
                <input type="text" name="price" placeholder="单价">
            </td>
            <td>
                <input type="text" name="stock" placeholder="库存量">
            </td>

            <td>
                <input type="button" onclick="findById()"  value=查询>
            </td>
            <td>
                <input type="button" onclick="add()" value=添加>
            </td>
            <td>
                <input type="submit"  onclick="remove()"   value=删除>
            </td>
            <td>
                <input type="submit"  onclick="update()"  value=修改>
            </td>
        </tr>
    </table>
</form>

<table border="1" align="center">
    <tr bgcolor="#949494">
        <th>产品ID</th><th>产品名</th><th>价格</th><th>库存</th>
    </tr>
    <c:forEach items="${products}" var="pr">
        <tr>
            <td align="center">${pr.productId}</td>
            <td align="center">${pr.productName}</td>
            <td align="center">${pr.price}</td>
            <td align="center">${pr.stock}</td>
        </tr>
    </c:forEach>

</table>
<script language="JavaScript">
    function findById(){
        document.form2.action="${pageContext.request.contextPath}/findProduct";
        document.form2.method="post";
        document.form2.submit();
    }
    function add(){
        document.form2.action="${pageContext.request.contextPath}/addProduct";
        document.form2.method="post";
        document.form2.submit();
    }
    function remove(){
        document.form2.action="${pageContext.request.contextPath}/removeProduct";
        document.form2.method="post";
        document.form2.submit();
    }
    function update(){
        document.form2.action="${pageContext.request.contextPath}/updateProduct";
        document.form2.method="post";
        document.form2.submit();
    }

</script>
<body/>
<html/>