<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${cookie.containsKey('locale') ? cookie['locale'].value : 'en-EN'}"/>
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

            <div style="float: left; width: 40%">
                <table align="left">
                    <tr>
                        <td><fmt:message key="admin.table.user" bundle="${rb}"/>: &nbsp </td>
                        <td>${subscriber.name} ${subscriber.middleName} ${subscriber.surname}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="admin.table.phone.number" bundle="${rb}"/>: &nbsp </td>
                        <td>${subscriber.phoneNumber}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="admin.table.debt" bundle="${rb}"/>: &nbsp </td>
                        <td><ctg:price-format price="${debt}"/> (&#8372)</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="admin.table.status" bundle="${rb}"/>: &nbsp </td>
                        <td>
                            <c:choose>
                                <c:when test="${subscriber.isBlocked()}">
                                    <fmt:message key="admin.table.blocked" bundle="${rb}"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="admin.table.active" bundle="${rb}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <c:choose>
                                <c:when test="${subscriber.isBlocked()}">
                                    <form action="/phone_station/unblock_user?user_id=${subscriber.id}" method="POST">
                                        <input type="submit" class="my-button"
                                               value=<fmt:message key="admin.table.unblock" bundle="${rb}"/>>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="/phone_station/block_user?user_id=${subscriber.id}" method="POST">
                                        <input type="submit" class="my-button"
                                               value=<fmt:message key="admin.table.block" bundle="${rb}"/>>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </table>
            </div>

            <div style="float: right; width: 60%">
                <table class="my-table" border="1" cellspacing="0">
                    <thead>
                    <tr>
                        <th><fmt:message key="admin.table.date" bundle="${rb}"/></th>
                        <th><fmt:message key="admin.table.description" bundle="${rb}"/></th>
                        <th><fmt:message key="admin.table.price" bundle="${rb}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="invoice" items="${subscriber.invoices}">
                        <tr>
                            <td><ctg:date-format dateTime="${invoice.dateTime}" locale="${sessionScope.locale}"/></td>
                            <td><c:out value="${invoice.description}"/></td>
                            <td><ctg:price-format price="${invoice.price}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/WEB-INF/view/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
