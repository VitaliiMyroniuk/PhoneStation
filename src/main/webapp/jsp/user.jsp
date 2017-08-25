<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>User page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header" align="right">
        <jsp:include page="/jsp/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/jsp/menu.jsp"/>
        </div>

        <div class="main">
            <h3>User page</h3>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/footer.jsp"/>
    </div>
</div>
</body>
</html>
