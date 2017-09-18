<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>User page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/jsp/user/user_menu.jsp"/>
        </div>

        <div class="main">
            <h3><fmt:message key="user.profile" bundle="${rb}"/></h3>
            <br>
            <table>
                <tr>
                    <td><fmt:message key="user.profile.name" bundle="${rb}"/>: &nbsp </td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td><fmt:message key="user.profile.middle.name" bundle="${rb}"/>: &nbsp </td>
                    <td>${user.middleName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="user.profile.surname" bundle="${rb}"/>: &nbsp </td>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <td><fmt:message key="user.profile.phone.number" bundle="${rb}"/>: &nbsp </td>
                    <td>${user.phoneNumber}</td>
                </tr>
                <tr>
                    <td><fmt:message key="user.profile.balance" bundle="${rb}"/>: &nbsp </td>
                    <td>${user.balance}</td>
                </tr>
                <tr>
                    <td><fmt:message key="user.profile.login" bundle="${rb}"/>: &nbsp </td>
                    <td>${user.account.login}</td>
                </tr>
                <tr>
                    <td><fmt:message key="user.profile.password" bundle="${rb}"/>: &nbsp </td>
                    <td>${user.account.password}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
