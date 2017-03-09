<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <name>Person Management Application</name>
</head>
<body>
<center>
    <h1>Person Management</h1>
    <h2>
        <a href="new">Add Person</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">Person List</a>

    </h2>
</center>
<div align="center">
    <c:if test="${person != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${person == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${person != null}">
                            Edit Person
                        </c:if>
                        <c:if test="${person == null}">
                            Add New Person
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${person != null}">
                    <input type="hidden" name="id" value="<c:out value='${person.id}' />" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${person.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Surname: </th>
                    <td>
                        <input type="text" name="surname" size="45"
                               value="<c:out value='${person.surname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Middlename: </th>
                    <td>
                        <input type="text" name="middlename" size="45"
                               value="<c:out value='${person.middlename}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Phone numbers:</th>
                    <td> <c:forEach var="phone" items="${listPersonPhone}">
                        <c:out value="${phone.number}" />
                        <!--Align hrefs to right-->
                        <a href="deletePhone?id=<c:out value='${phone.id}' />"style="float:right;margin-right:1%">Delete </a>&nbsp;
                        <a href="editPhone?id=<c:out value='${phone.id}' />"  style="float:right;margin-right:5%">Edit</a>
                        <br>
                </c:forEach>
                          <a href="newPhone">Add phone</a>
                    </td>
                </tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save person" />
                        <br>
                        <a href="list">Back to person list</a>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
