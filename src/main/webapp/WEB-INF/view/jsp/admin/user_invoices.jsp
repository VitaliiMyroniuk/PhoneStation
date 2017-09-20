<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Invoices page</title>
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
            <h3><fmt:message key="admin.table.invoices" bundle="${rb}"/></h3>
            <br>
            <table>
                <tr>
                    <td><fmt:message key="admin.table.user" bundle="${rb}"/>: </td>
                    <td>${subscriber.name} ${subscriber.middleName} ${subscriber.surname}</td>
                </tr>
                <tr>
                    <td><fmt:message key="admin.table.phone.number" bundle="${rb}"/>: </td>
                    <td>${subscriber.phoneNumber}</td>
                </tr>
            </table>
            <br>
            <table class="my-table" border="1" cellspacing="0">
                <thead>
                <tr>
                    <th><fmt:message key="admin.table.date" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.description" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.table.price" bundle="${rb}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="invoice" items="${user_invoices}">
                    <tr>
                        <td><c:out value="${invoice.dateTime}"/></td>
                        <td><c:out value="${invoice.description}"/></td>
                        <td><c:out value="${invoice.price}"/></td>
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
