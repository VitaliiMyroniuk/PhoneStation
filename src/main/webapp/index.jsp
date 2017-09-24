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
        <jsp:include page="/WEB-INF/view/jsp/general/header.jsp"/>
    </div>

    <div class="content-block" style="display: flex">
        <div class="login-container">
            <form action="/controller" method="POST">
                <input type="hidden" name="query" value="login"/>
                <table>
                    <tr>
                        <td colspan="2" align="center">
                            <h2><fmt:message key="login.authentication" bundle="${rb}"/></h2>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td colspan="2"><fmt:message key="login.login" bundle="${rb}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="my-input" type="text" name="login"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><fmt:message key="login.password" bundle="${rb}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="my-input" type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td>
                            <input class="my-button" type="submit"
                                   value="<fmt:message key="login.sign.in" bundle="${rb}"/>">
                        </td>
                        <td style="text-align: right">
                            <a href="/controller?query=registration">
                                <fmt:message key="login.sign.up" bundle="${rb}"/>
                            </a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/WEB-INF/view/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>