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


<div align="center">
    <c:if test="${phone != null}">
    <form action="updatePhone" method="post">
        </c:if>
        <c:if test="${phone == null}">
        <form action="insertPhone" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${phone != null}">
                            Edit phone number
                        </c:if>
                        <c:if test="${phone == null}">
                            Add new phone number
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${phone != null}">
                    <input type="hidden" name="id" value="<c:out value='${phone.id}' />" />
                </c:if>
                <tr>
                    <th colspan="2" align="left"> Phone number owner : <c:out value="${person.name}" />
                        <c:out value="${person.surname}" />
                        <c:out value="${person.middlename}" /> </th>
                    <tr>
                    <th>Number: </th>
                    <td>

                        <input type="text" name="number" size="45"
                               value="<c:out value='${phone.number}' />"
                        />
                    </td>
                <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Add phone" />
                    <br>
                    <a href="list">back to person information</a>
                </td>
                </tr>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
