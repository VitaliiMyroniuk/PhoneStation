<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Knight info</title>
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
            Sir ${knight.getName()} has the following ammunition:

            <br> <br>
            <table border="1" cellspacing="0" cellpadding="2">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>
                        Weight (kg)
                        <a href="/infoKnight?id=${knight.getId()}&sort=weightAscending">&#9650</a>
                        <a href="/infoKnight?id=${knight.getId()}&sort=weightDescending">&#9660</a>
                    </th>
                    <th>
                        Price ($)
                        <a href="/infoKnight?id=${knight.getId()}&sort=priceAscending">&#9650</a>
                        <a href="/infoKnight?id=${knight.getId()}&sort=priceDescending">&#9660</a>
                    </th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td></td>
                    <td></td>
                    <td><c:out value="${ammunitionPrice}"/></td>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="thing" items="${knight.getAmmunition()}">
                    <tr>
                        <td><c:out value="${thing.getClass().getSimpleName()}"/></td>
                        <td><c:out value="${thing.getWeight()}"/></td>
                        <td><c:out value="${thing.getPrice()}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <br> <br>
            Show ammunition with price:
            <br> <br>
            <form action="/infoKnight" method="GET">
                <table>
                    <tr>
                        <td>from</td>
                        <td><input type="number" name="from"></td>
                    </tr>
                    <tr>
                        <td>to</td>
                        <td><input type="number" name="to"></td>
                    </tr>
                </table>
                <br>
                <input type="hidden" name="id" value="${knight.getId()}">
                <input type="submit" value="Show"> <br>
            </form>

            <table border="1" cellspacing="0" cellpadding="2">
                <thead>
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Weight (kg)
                    </th>
                    <th>
                        Price ($)
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="thing" items="${list}">
                    <tr>
                        <td><c:out value="${thing.getClass().getSimpleName()}"/></td>
                        <td><c:out value="${thing.getWeight()}"/></td>
                        <td><c:out value="${thing.getPrice()}"/></td>
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