<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setLocale value="${sessionScope.locale}" scope="session"/>--%>
<fmt:setLocale value="uk_UA" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>User page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header" align="right">
        <jsp:include page="/jsp/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/jsp/user_menu.jsp"/>
        </div>

        <div class="main">
            <h3>User page</h3>
            <br>
            <table>
                <tr>
                    <td>Name: </td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td>Surname: </td>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <td>Passport: </td>
                    <td>${user.passport}</td>
                </tr>
                <tr>
                    <td>Login: </td>
                    <td>${user.login}</td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td>${user.password}</td>
                </tr>
                <tr>
                    <td>Sim card number: </td>
                    <td>${user.simCard.number}</td>
                </tr>
                <tr>
                    <td>Sim card balance: </td>
                    <td>${user.simCard.balance}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/footer.jsp"/>
    </div>
</div>
</body>
</html>
