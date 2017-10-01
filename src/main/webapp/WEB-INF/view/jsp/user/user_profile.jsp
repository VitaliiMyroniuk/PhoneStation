<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>User profile page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/WEB-INF/view/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/WEB-INF/view/jsp/user/user_menu.jsp"/>
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
                    <td><ctg:price-format price="${user.balance}"/> &#8372</td>
                </tr>
                <tr>
                    <td><fmt:message key="user.profile.status" bundle="${rb}"/>: &nbsp </td>
                    <td>
                        <c:choose>
                            <c:when test="${user.isBlocked()}">
                                <fmt:message key="user.profile.blocked" bundle="${rb}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="user.profile.active" bundle="${rb}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/WEB-INF/view/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
