<%--
  Created by IntelliJ IDEA.
  User: clguigue
  Date: 2019-10-27
  Time: 12:18 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>${title}</title>
    </head>
    <body>
        <img src="images/alithya.jpg"/>
        <h2>${title}</h2>
        <table>
            <tr>
                <form action="add" method="POST">
                    <td><input type="text" name="firstname"/></td>
                    <td><input type="text" name="lastname"/></td>
                    <td><input type="submit" value="+"/></td>
                </form>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.firstname}"/></td>
                    <td><c:out value="${user.lastname}"/></td>
                    <td>
                        <form action="delete" method="POST">
                            <input type="hidden" value="${user.id}" name="id"/>
                            <input type="submit" value="-">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
