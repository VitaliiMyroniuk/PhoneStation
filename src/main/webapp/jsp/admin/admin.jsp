<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Admin page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/jsp/admin/admin_menu.jsp"/>
        </div>

        <div class="main">
            <h3>
                <fmt:message key="admin.hello" bundle="${rb}"/>, ${user.name} ${user.surname}!</h3>
            </h3>
            <br>
            <fmt:message key="admin.info" bundle="${rb}"/>
            <br><br>
            <table>
                <tr>
                    <td> <fmt:message key="admin.users" bundle="${rb}"/>: &nbsp </td>
                    <td>${user_count_info[0]}</td>
                </tr>
                <tr>
                    <td><fmt:message key="admin.new.users" bundle="${rb}"/>: &nbsp </td>
                    <td>${user_count_info[1]}</td>
                </tr>
                <tr>
                    <td><fmt:message key="admin.debtors" bundle="${rb}"/>: &nbsp </td>
                    <td>${user_count_info[2]}</td>
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