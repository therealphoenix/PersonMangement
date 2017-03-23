<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    <%@include file="js/checkPhoneInputScript.js"%>
</script>
<html>
<head>
    <!-- Bootstrap -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    </head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <span class="label label-info">TAT</span>
        <h1>Phonebook application</h1>
    </div>
</div>
<div align="center">
    <c:if test="${phone != null}">
    <form action="updatephone" method="post">
        </c:if>
        <c:if test="${phone == null}">
        <form action="insertphone" method="post">
            </c:if>
            <table border="2" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${phone != null}">
                            <h2>Edit phone number</h2>
                        </c:if>
                        <c:if test="${phone == null}">
                            <h2>Add new phone number</h2>
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${phone != null}">
                <input type="hidden" name="id" value="<c:out value='${phone.id}' />"/>
                </c:if>
                <tr>
                    <th bgcolor="#F2F3F4" colspan="2" align="left"><br>&nbsp Phone number owner : <c:out
                            value="${person.name}"/>

                        <c:out value="${person.surname}"/>
                        <c:out value="${person.middlename}"/>
                        <br>
                        <br>
                    </th>
                </tr>
                <tr>
                    <th bgcolor="#76D7C4">&nbsp Number:&nbsp</th>
                    <td>
                       <div form class = "bs-example bs-example-form" role = "form">
                        <div class="input-group input-group-lg">
                            <!--Checking length size and creating pop-up message-->
                            <!--Restrict input disallowed  symbols-->
                            <input type="text" name="number" size="45" input pattern=".{2,50}" required
                                   title="2 to 50 characters : [ numbers,-,+,# ]"
                                   class="form-control" placeholder="Phonenumber"
                                   oninput="checkUserInputPhoneNumber (this)"
                                   onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                                   value="<c:out value='${phone.number}' />"
                            />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#F2F3F4" colspan="2" align="center">
                        <input type="hidden" name="from" value="edit?id=${person.id}">
                        <c:if test="${phone != null}">
                        <br>
                        <input type="submit" button type="submit" class="btn btn-warning" value="Edit phone number"
                               id="Edit"/>
                        </c:if>
                        <c:if test="${phone == null}">
                        <br>
                        <input type="submit" button type="submit" class="btn btn-warning" value="Add phone number"
                               id="Add"/>
                        </c:if>

        </form>
        <br>
        <button type="button" class="btn btn-link"
                onclick="window.location.href='edit?id=<c:out value='${person.id}'/>'">Back to person information
        </button>
    </form>
</div>

</body>
</html>

