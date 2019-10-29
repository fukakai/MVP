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
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div  id="imageAlithya">
            <img src="images/alithya.jpg"/>
        </div>
        <div id="addTable">
            <h2>Users Board Manager</h2>
            <table class="tableArea">
                <tr>
                    <form action="add" method="POST">
                        <td>Firstname: <input type="text" name="firstname" placeholder="First Name"/></td>
                        <td>Lastname: <input type="text" name="lastname" placeholder="Last Name"/></td>
                        <td><input type="submit" value="+"/></td>
                    </form>
                </tr>
            </table>
        </div>
        <div id="listTable">
            <table class="tableArea">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
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
        </div>
    </body>
</html>
