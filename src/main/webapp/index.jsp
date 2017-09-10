<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale" value="${not empty sessionScope.locale ? sessionScope.locale : 'uk_UA'}" scope="session"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Login page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <form action="/controller" method="POST">
            <h3><fmt:message key="login.authentication" bundle="${rb}"/></h3>
            <input type="hidden" name="query" value="login"/>
            <table>
                <tr>
                    <td><fmt:message key="login.login" bundle="${rb}"/></td>
                    <td><input type="text" name="login"></td>
                </tr>
                <tr>
                    <td><fmt:message key="login.password" bundle="${rb}"/></td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value=<fmt:message key="login.enter" bundle="${rb}"/>>
                    </td>
                    <td>
                        <a href="/controller?query=registration">
                            <fmt:message key="login.registration" bundle="${rb}"/>
                        </a>
                    </td>
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