<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Debtors page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/WEB-INF/view/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/WEB-INF/view/jsp/admin/admin_menu.jsp"/>
        </div>

        <div class="main">
            <h3><fmt:message key="admin.debtors" bundle="${rb}"/></h3>
            <br>
            <table class="my-table" border="1" cellspacing="0">
                <thead>
                <tr>
                    <th><fmt:message key="admin.table.name" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.middle_name" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.surname" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.phone.number" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.balance" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.status" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.invoices" bundle="${rb}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${debtors}">
                    <tr>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.middleName}"/></td>
                        <td><c:out value="${user.surname}"/></td>
                        <td><c:out value="${user.phoneNumber}"/></td>
                        <td><ctg:price-format price="${user.balance}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.isBlocked()}">
                                    <fmt:message key="admin.table.blocked" bundle="${rb}"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="admin.table.active" bundle="${rb}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="/phone_station/user_invoices?user_id=${user.id}">
                                <fmt:message key="admin.table.show" bundle="${rb}"/>
                            </a>
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
