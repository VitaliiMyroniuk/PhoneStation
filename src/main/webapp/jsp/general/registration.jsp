<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Registration page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <form action="/controller" method="POST">
            <h3><fmt:message key="registration" bundle="${rb}"/></h3>
            <input type="hidden" name="query" value="registration"/>
            <table>
                <tr>
                    <td><fmt:message key="registration.name" bundle="${rb}"/></td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.middle_name" bundle="${rb}"/></td>
                    <td><input type="text" name="middle_name"></td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.surname" bundle="${rb}"/></td>
                    <td><input type="text" name="surname"></td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.phone_number" bundle="${rb}"/></td>
                    <td><input type="text" name="phone_number"></td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.login" bundle="${rb}"/></td>
                    <td><input type="text" name="login"></td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.password" bundle="${rb}"/></td>
                    <td><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td><input type="submit" value=<fmt:message key="registration.send" bundle="${rb}"/>></td>
                </tr>
            </table>
        </form>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>