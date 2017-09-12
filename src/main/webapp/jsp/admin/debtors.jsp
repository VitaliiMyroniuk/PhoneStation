<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Debtors page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/jsp/admin/admin_menu.jsp"/>
        </div>

        <div class="main">
            <h3>Debtors</h3>
            <br>
            <table border="1" cellspacing="0" cellpadding="2">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Middle name</th>
                    <th>Surname</th>
                    <th>Phone number</th>
                    <th>Balance</th>
                    <th>Invoices</th>
                    <th>Block</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${debtors}">
                    <tr>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.middleName}"/></td>
                        <td><c:out value="${user.surname}"/></td>
                        <td><c:out value="${user.phoneNumber}"/></td>
                        <td><c:out value="${user.balance}"/></td>
                        <td><a href="/controller?query=show_invoices&user_id=${user.id}">show</a></td>
                        <td><a href="/controller?query=block_user&user_id=${user.id}">block</a></td>
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
