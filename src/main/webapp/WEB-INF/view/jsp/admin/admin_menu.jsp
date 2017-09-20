<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<ul>
    <li>
        <a href="/controller?query=profile">
            <fmt:message key="menu.profile" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=users">
            <fmt:message key="menu.users" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=new_users">
            <fmt:message key="menu.new.users" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="/controller?query=debtors">
            <fmt:message key="menu.debtors" bundle="${rb}"/>
        </a>
    </li>
</ul>
