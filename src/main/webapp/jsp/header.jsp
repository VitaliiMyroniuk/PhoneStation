<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setLocale value="${sessionScope.locale}" scope="session"/>--%>
<fmt:setLocale value="uk_UA" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<fmt:message key="header.language" bundle="${rb}"/>:
<a href="#&locale=en_GB">Eng</a>
<a href="#&locale=uk_UA">Укр</a>

<%--<form action="/controller?query=language" method="POST">--%>
    <%--<select name="language" onchange="submit()">--%>
        <%--<option value="en_GB" ${locale == 'en_GB' ? 'selected' : ''}>Eng</option>--%>
        <%--<option value="uk_UA" ${locale == 'uk_UA' ? 'selected' : ''}>Укр</option>--%>
    <%--</select>--%>
<%--</form>--%>