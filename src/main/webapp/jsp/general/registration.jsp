<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Registration page</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <form action="/controller" method="POST">
            <input type="hidden" name="query" value="registration"/>
            <table>
                <tr>
                    <td colspan="2" align="left">
                        <h3><fmt:message key="registration" bundle="${rb}"/></h3>
                    </td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.name" bundle="${rb}"/> &nbsp </td>
                    <td><input type="text" name="name" value=${name}></td>
                    <td>
                        <c:choose>
                            <c:when test="${name_is_valid == null}">
                            </c:when>
                            <c:when test="${name_is_valid}">
                                <p style="color: lawngreen"> &#10004 </p>
                            </c:when>
                            <c:otherwise>
                                <p style="color: red"> * </p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.middle.name" bundle="${rb}"/> &nbsp </td>
                    <td><input type="text" name="middle_name" value=${middle_name}></td>
                    <td>
                        <c:choose>
                            <c:when test="${middle_name_is_valid == null}">
                            </c:when>
                            <c:when test="${middle_name_is_valid}">
                                <p style="color: lawngreen"> &#10004 </p>
                            </c:when>
                            <c:otherwise>
                                <p style="color: red"> * </p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.surname" bundle="${rb}"/> &nbsp </td>
                    <td><input type="text" name="surname" value=${surname}></td>
                    <td>
                        <c:choose>
                            <c:when test="${surname_is_valid == null}">
                            </c:when>
                            <c:when test="${surname_is_valid}">
                                <p style="color: lawngreen"> &#10004 </p>
                            </c:when>
                            <c:otherwise>
                                <p style="color: red"> * </p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.phone.number" bundle="${rb}"/> &nbsp </td>
                    <td><input type="text" name="phone_number" value=${phone_number}></td>
                    <td>
                        <c:choose>
                            <c:when test="${phone_number_is_valid == null}">
                            </c:when>
                            <c:when test="${phone_number_is_valid}">
                                <p style="color: lawngreen"> &#10004 </p>
                            </c:when>
                            <c:otherwise>
                                <p style="color: red"> * </p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.login" bundle="${rb}"/> &nbsp </td>
                    <td><input type="text" name="login" value=${login}></td>
                    <td>
                        <c:choose>
                            <c:when test="${login_is_valid == null}">
                            </c:when>
                            <c:when test="${login_is_valid}">
                                <p style="color: lawngreen"> &#10004 </p>
                            </c:when>
                            <c:otherwise>
                                <p style="color: red"> * </p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.password" bundle="${rb}"/> &nbsp </td>
                    <td><input type="text" name="password"></td>
                    <td>
                        <c:choose>
                            <c:when test="${password_is_valid == null}">
                            </c:when>
                            <c:when test="${password_is_valid}">
                                <p style="color: lawngreen"> &#10004 </p>
                            </c:when>
                            <c:otherwise>
                                <p style="color: red"> * </p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="registration.confirm.password" bundle="${rb}"/> &nbsp </td>
                    <td><input type="text" name="confirmed_password"></td>
                    <td>
                        <c:choose>
                            <c:when test="${confirmed_password_is_valid == null}">
                            </c:when>
                            <c:when test="${confirmed_password_is_valid}">
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
                    <td colspan="2" align="left">
                        <input type="submit" value=<fmt:message key="registration.send" bundle="${rb}"/>>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>