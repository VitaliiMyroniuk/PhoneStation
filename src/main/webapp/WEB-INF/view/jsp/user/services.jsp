<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Services page</title>
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
            <h3><fmt:message key="user.services" bundle="${rb}"/></h3>
            <br>
            <table class="my-table" border="1" cellspacing="0">
                <thead>
                <tr>
                    <th><fmt:message key="user.table.name" bundle="${rb}"/></th>
                    <th><fmt:message key="user.table.description" bundle="${rb}"/></th>
                    <th><fmt:message key="user.table.price" bundle="${rb}"/></th>
                    <th><fmt:message key="user.table.status" bundle="${rb}"/></th>
                    <th><fmt:message key="user.table.change.status" bundle="${rb}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="service" items="${all_services}">
                    <tr>
                        <td><c:out value="${service.name}"/></td>
                        <td><c:out value="${service.description}"/></td>
                        <td><c:out value="${service.price}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user_services.contains(service)}">
                                    <fmt:message key="user.table.active" bundle="${rb}"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="user.table.inactive" bundle="${rb}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user_services.contains(service)}">
                                    <a href="/controller?query=switch_off_service&service_id=${service.id}">
                                        <fmt:message key="user.table.switch.off" bundle="${rb}"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/controller?query=switch_on_service&service_id=${service.id}">
                                        <fmt:message key="user.table.switch.on" bundle="${rb}"/>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/WEB-INF/view/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
