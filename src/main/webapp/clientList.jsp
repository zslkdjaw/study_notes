<%--
  Created by IntelliJ IDEA.
  User: dooo
  Date: 2022/5/8
  Time: 15:17
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
<h2 align="center">客户表</h2>
<form  name="form1">
    <table>
        <tr >
            <td>
                <input type="text" name="clientId" placeholder="客户ID" >
            </td>
            <td>
                <input type="text" name="clientName" placeholder="客户名">
            </td>
            <td>
                <input type="text" name="sex" placeholder="性别">
            </td>
            <td>
                <input type="text" name="age" placeholder="年龄">
            </td>
            <td>
                <input type="text" name="birth" placeholder="生日格式:yy-mm-dd">
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
        <th>客户ID</th><th>客户名</th><th>性别</th><th>年龄</th>
    </tr>
    <c:forEach items="${clients}" var="cl">
        <tr>
            <td align="center">${cl.clientId}</td>
            <td align="center">${cl.clientName}</td>
            <td align="center">${cl.sex}</td>
            <td align="center">${cl.age}</td>
        </tr>
    </c:forEach>

</table>

<script language="JavaScript">
    function findById(){
        document.form1.action="${pageContext.request.contextPath}/findClient";
        document.form1.method="post";
        document.form1.submit();
    }
    function add(){
        document.form1.action="${pageContext.request.contextPath}/addClient";
        document.form1.method="post";
        document.form1.submit();
    }
    function remove(){
        document.form1.action="${pageContext.request.contextPath}/removeClient";
        document.form1.method="post";
        document.form1.submit();
    }
    function update(){
        document.form1.action="${pageContext.request.contextPath}/updateClient";
        document.form1.method="post";
        document.form1.submit();
    }

</script>
</body>
</html>
