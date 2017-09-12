<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Account refill page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
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
            <h3>Account refill</h3>
            <br>
            <form action="/controller" method="POST">
                <input type="hidden" name="query" value="account_refill"/>
                <table>
                    <tr>
                        <td><fmt:message key="account.refill.credit.card" bundle="${rb}"/></td>
                        <td><input type="text" name="credit_card"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="account.refill.cvv" bundle="${rb}"/></td>
                        <td><input type="text" name="cvv"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="account.refill.sum" bundle="${rb}"/></td>
                        <td><input type="text" name="sum"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value=<fmt:message key="account.refill.confirm" bundle="${rb}"/>></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>