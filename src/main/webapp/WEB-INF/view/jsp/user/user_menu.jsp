<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.containsKey('locale') ? cookie['locale'].value : 'en-EN'}"/>
<fmt:setBundle basename="locale" var="rb"/>

<ul>
    <li>
        <a href="/phone_station/user_profile">
            <fmt:message key="menu.profile" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/phone_station/services">
            <fmt:message key="menu.services" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/phone_station/invoices">
            <fmt:message key="menu.invoices" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/phone_station/account_refill">
            <fmt:message key="menu.account.refill" bundle="${rb}"/>
        </a>
    </li>
</ul>
