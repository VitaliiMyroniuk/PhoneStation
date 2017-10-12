<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<ul>
    <li>
        <a href="/phone_station/admin_profile">
            <fmt:message key="menu.profile" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/phone_station/users">
            <fmt:message key="menu.users" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/phone_station/new_users">
            <fmt:message key="menu.new.users" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/phone_station/debtors">
            <fmt:message key="menu.debtors" bundle="${rb}"/>
        </a>
    </li>
</ul>
