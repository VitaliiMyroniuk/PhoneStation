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
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/jsp/user/user_menu.jsp"/>
        </div>

        <div class="main">
            <h3>Invoices</h3>
            <br>
            <table border="1" cellspacing="0" cellpadding="2">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Pay</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="invoice" items="${invoices}">
                    <tr>
                        <td><c:out value="${invoice.dateTime}"/></td>
                        <td><c:out value="${invoice.description}"/></td>
                        <td><c:out value="${invoice.price}"/></td>
                        <td>
                            <a href="/controller?query=pay_invoice&invoice_id=${invoice.id}">pay</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
