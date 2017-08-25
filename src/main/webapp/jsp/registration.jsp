<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Registration page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header" align="right">
        <jsp:include page="/jsp/header.jsp"/>
    </div>

    <div class="content-block">
        <form action="/controller" method="POST">
            <h3>Registration</h3>
            <input type="hidden" name="query" value="registration"/>
            <table>
                <tr>
                    <td><input type="text" name="phoneNumber" placeholder="phone number"></td>
                </tr>
                <tr>
                    <td><input type="text" name="name" placeholder="name"></td>
                </tr>
                <tr>
                    <td><input type="text" name="surname" placeholder="surname"></td>
                </tr>
                <tr>
                    <td><input type="text" name="passport" placeholder="passport"></td>
                </tr>
                <tr>
                    <td><input type="text" name="login" placeholder="login"></td>
                </tr>
                <tr>
                    <td><input type="text" name="password" placeholder="password"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Send"></td>
                </tr>
            </table>
        </form>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/footer.jsp"/>
    </div>
</div>
</body>
</html>