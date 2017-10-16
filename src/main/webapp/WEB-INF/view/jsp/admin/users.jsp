<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Users page</title>
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
            <div style="height: 520px">
                <h3><fmt:message key="admin.users" bundle="${rb}"/></h3>
                <br>
                <table class="my-table" border="1" cellspacing="0">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th><fmt:message key="admin.table.name" bundle="${rb}"/></th>
                        <th><fmt:message key="admin.table.middle_name" bundle="${rb}"/></th>
                        <th><fmt:message key="admin.table.surname" bundle="${rb}"/></th>
                        <th><fmt:message key="admin.table.phone.number" bundle="${rb}"/></th>
                        <th><fmt:message key="admin.table.delete" bundle="${rb}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}" varStatus="status" begin="0" step="1">
                        <tr>
                            <td><c:out value="${status.count + from}"/></td>
                            <td><c:out value="${user.name}"/></td>
                            <td><c:out value="${user.middleName}"/></td>
                            <td><c:out value="${user.surname}"/></td>
                            <td><c:out value="${user.phoneNumber}"/></td>
                            <td>
                                <a href="/phone_station/delete_user?user_id=${user.id}">
                                    <fmt:message key="admin.table.delete.user" bundle="${rb}"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div align="center">
                <tr>
                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td><text style="color: red">${i}</text></td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="/phone_station/users?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </div>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/WEB-INF/view/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
