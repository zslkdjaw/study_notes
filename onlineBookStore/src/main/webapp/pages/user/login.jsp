<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>铛铛铛铛会员登录页面</title>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>

<div id="login_header">
    <img class="logo_img" alt="" src="static/img/DDlogoNEW.gif">
</div>
<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>铛铛铛铛会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%>--%>
									${empty requestScope.msg ? "请输入用户名和密码":requestScope.msg}
									</span>
                </div>
                <div class="form">
                    <form action="userservlet" method="post">
                        <input type="hidden" value="login" name="action">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username"
                               value="${cookie.user.value.split(":")[0]}"/>

                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password"
                               value="${cookie.user.value.split(":")[1]}"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/bottom.jsp" %>
</body>
</html>