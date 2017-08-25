<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Home page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header" align="right">
        <jsp:include page="/pages/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/pages/menu.jsp"/>
        </div>

        <div class="main">
            <table border="1" cellspacing="0" cellpadding="2">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Ammunition</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="knight" items="${knights}">
                    <tr>
                        <td><c:out value="${knight.getId()}"/></td>
                        <td><c:out value="${knight.getName()}"/></td>
                        <td><a href="/infoKnight?id=${knight.getId()}">show</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/pages/footer.jsp"/>
    </div>
</div>
</body>
</html>
