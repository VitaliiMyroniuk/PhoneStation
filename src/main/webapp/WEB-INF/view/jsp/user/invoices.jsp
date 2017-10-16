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
            <jsp:include page="/WEB-INF/view/jsp/user/user_menu.jsp"/>
        </div>

        <div class="main">
            <h3><fmt:message key="user.invoices" bundle="${rb}"/></h3>
            <br>
            <c:if test="${param.not_enough_money}">
                <div style="color: red">
                    <fmt:message key="user.not.enough.money" bundle="${rb}"/>
                </div>
            </c:if>
            <br>
            <table class="my-table" border="1" cellspacing="0">
                <thead>
                <tr>
                    <th><fmt:message key="user.table.date" bundle="${rb}"/></th>
                    <th><fmt:message key="user.table.description" bundle="${rb}"/></th>
                    <th><fmt:message key="user.table.price" bundle="${rb}"/></th>
                    <th><fmt:message key="user.table.pay" bundle="${rb}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="invoice" items="${invoices}">
                    <tr>
                        <td>
                            <ctg:date-format dateTime="${invoice.dateTime}" locale="${sessionScope.locale}"/>
                        </td>
                        <td><c:out value="${invoice.description}"/></td>
                        <td>
                            <ctg:price-format price="${invoice.price}"/>
                        </td>
                        <td>
                            <a href="/phone_station/pay_invoice?invoice_id=${invoice.id}">
                                <fmt:message key="user.table.pay.invoice" bundle="${rb}"/>
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
