<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--&lt;%&ndash;<fmt:setLocale value="${sessionScope.locale}" scope="session"/>&ndash;%&gt;--%>
<fmt:setLocale value="uk_UA" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<ul>
    <li>
        <a href="/controller?query=profile">
            <fmt:message key="menu.profile" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=services">
            <fmt:message key="menu.services" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=accountRefill">
            <fmt:message key="menu.account.refill" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=makeCall">
            <fmt:message key="menu.make.call" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=logout">
            <fmt:message key="menu.logout" bundle="${rb}"/>
        </a>
    </li>
</ul>
