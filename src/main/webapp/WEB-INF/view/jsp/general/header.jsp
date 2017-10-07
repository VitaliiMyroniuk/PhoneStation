<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<table width="100%">
    <tr>
        <td width="90%" align="left">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="/phone_station/profile">
                        <fmt:message key="header.phone.station" bundle="${rb}"/>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="/phone_station/login">
                        <fmt:message key="header.phone.station" bundle="${rb}"/>
                    </a>
                </c:otherwise>
            </c:choose>
        </td>
        <td width="5%" align="right">
            <form action="/phone_station/language" method="POST">
                <input type="hidden" name="pagePath" value="${pageContext.request.requestURI}">
                <input type="hidden" name="queryString" value="${pageContext.request.queryString}">
                <select name="locale" onchange="submit()">
                    <option value="en_GB" ${sessionScope.locale == 'en_GB' ? 'selected' : ''}>Eng</option>
                    <option value="uk_UA" ${sessionScope.locale == 'uk_UA' ? 'selected' : ''}>Укр</option>
                </select>
            </form>
        </td>
        <td width="5%" align="right">
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <a href="/phone_station/logout">
                        <fmt:message key="header.logout" bundle="${rb}"/>
                    </a>
                </c:when>
            </c:choose>
        </td>
    </tr>
</table>


