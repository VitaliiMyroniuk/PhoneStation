<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setLocale value="${sessionScope.locale}" scope="session"/>--%>
<fmt:setLocale value="uk_UA" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<ul>
    <li>
        <a href="/controller?query=profile">
            <fmt:message key="admin.profile" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=register">
            <fmt:message key="admin.registration" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=debtors">
            <fmt:message key="admin.debtors" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=logout">
            <fmt:message key="admin.logout" bundle="${rb}"/>
        </a>
    </li>
</ul>
