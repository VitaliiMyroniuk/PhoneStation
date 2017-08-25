<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Add knight</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header" align="right">
        <jsp:include page="/pages/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/pages/menu.jsp"/>
        </div>

        <div class="main">
            <form action="/addKnight" method="POST">
                <table>
                    <tr>
                        <td>Name</td>
                        <td>&nbsp<input type="text" name="name" value=""></td>
                        <td><font color="red">${wrongName}</font></td>
                    </tr>
                    <tr>
                        <td>Boots</td>
                        <td>
                            <input type="radio" name="boots" value="3 10" checked>
                                weight: &nbsp 3kg &nbsp&nbsp price: 10$
                            </input><br>
                            <input type="radio" name="boots" value="2 15">
                                weight: &nbsp 2kg &nbsp&nbsp price: 15$
                            </input><br>
                            <input type="radio" name="boots" value="1 20">
                                weight: &nbsp 1kg &nbsp&nbsp price: 20$
                            </input><br>
                        </td>
                    </tr>
                    <tr>
                        <td>Hauberk</td>
                        <td>
                            <input type="radio" name="hauberk" value="10 10" checked>
                                weight: 10kg &nbsp&nbsp price: 10$
                            </input><br>
                            <input type="radio" name="hauberk" value="20 15">
                                weight: 20kg &nbsp&nbsp price: 15$
                            </input><br>
                            <input type="radio" name="hauberk" value="5 20">
                                weight: &nbsp 5kg &nbsp&nbsp price: 20$
                            </input><br>
                        </td>
                    </tr>
                    <tr>
                        <td>Helmet</td>
                        <td>
                            <input type="radio" name="helmet" value="3 10" checked>
                                weight: &nbsp 3kg &nbsp&nbsp price: 10$
                            </input><br>
                            <input type="radio" name="helmet" value="5 15">
                                weight: &nbsp 5kg &nbsp&nbsp price: 15$
                            </input><br>
                            <input type="radio" name="helmet" value="2 20">
                                weight: &nbsp 2kg &nbsp&nbsp price: 20$
                            </input><br>
                        </td>
                    </tr>
                    <tr>
                        <td>Shield</td>
                        <td>
                            <input type="radio" name="shield" value="7 10" checked>
                                weight: &nbsp 7kg &nbsp&nbsp price: 10$
                            </input><br>
                            <input type="radio" name="shield" value="10 15">
                                weight: 10kg &nbsp&nbsp price: 15$
                            </input><br>
                            <input type="radio" name="shield" value="5 20">
                                weight: &nbsp 5kg &nbsp&nbsp price: 20$
                            </input><br>
                        </td>
                    </tr>
                    <tr>
                        <td>Sword</td>
                        <td>
                            <input type="radio" name="sword" value="5 10" checked>
                                weight: &nbsp 5kg &nbsp&nbsp price: 10$
                            </input><br>
                            <input type="radio" name="sword" value="9 15">
                                weight: &nbsp 9kg &nbsp&nbsp price: 15$
                            </input><br>
                            <input type="radio" name="sword" value="7 20">
                                weight: &nbsp 7kg &nbsp&nbsp price: 20$
                            </input><br>
                        </td>
                    </tr>
                    <tr>
                        <td height="20"></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2"><input type="submit" value="Add Knight"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/pages/footer.jsp"/>
    </div>
</div>
</body>
</html>