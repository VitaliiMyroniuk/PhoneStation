<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Account refill page</title>
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
            <h3><fmt:message key="user.account.refill" bundle="${rb}"/></h3>
            <br>
            <form action="/phone_station/account_refill" method="POST">
                <table>
                    <tr>
                        <td><fmt:message key="user.account.refill.credit.card" bundle="${rb}"/> &nbsp </td>
                        <td><input type="text" name="credit_card_number" value="${credit_card_number}"></td>
                        <td>
                            <c:choose>
                                <c:when test="${credit_card_number_is_valid == null}">
                                </c:when>
                                <c:when test="${credit_card_number_is_valid}">
                                    <p style="color: lawngreen"> &#10004 </p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: red"> * </p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.account.refill.cvv" bundle="${rb}"/> &nbsp </td>
                        <td><input type="text" name="cvv" value="${cvv}"></td>
                        <td>
                            <c:choose>
                                <c:when test="${cvv_is_valid == null}">
                                </c:when>
                                <c:when test="${cvv_is_valid}">
                                    <p style="color: lawngreen"> &#10004 </p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: red"> * </p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.account.refill.sum" bundle="${rb}"/> &nbsp </td>
                        <td><input type="text" name="sum" value="${sum}"></td>
                        <td>
                            <c:choose>
                                <c:when test="${sum_is_valid == null}">
                                </c:when>
                                <c:when test="${sum_is_valid}">
                                    <p style="color: lawngreen"> &#10004 </p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: red"> * </p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" style="padding: 2px 5px"
                                   value=<fmt:message key="user.account.refill.confirm" bundle="${rb}"/>>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/WEB-INF/view/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>