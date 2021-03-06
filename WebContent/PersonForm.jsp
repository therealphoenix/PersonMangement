<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    <%@include file="js/checkLineInput.js"%>
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
    <c:if test="${person != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${person == null}">
        <form action="insert" method="post">
            </c:if>
            <table class="table">
                <caption>
                    <h3>
                        <c:if test="${person != null}">
                            &nbsp Edit Person
                        </c:if>
                        <c:if test="${person == null}">
                            &nbsp Add New Person
                        </c:if>
                    </h3>
                </caption>
                <c:if test="${person != null}">
                    <input type="hidden" name="id" value="<c:out value='${person.id}' />"/>
                </c:if>
                <tr>
                    <th class="active">&nbsp Name:</th>
                    <td class="active">
                        <!--Checking length size and creating pop-up message-->
                        <!--Restrict input disallowed  symbols-->
                        <input type="text" name="name" size="45"  required
                               title="1 to 150 characters : [ letters, numbers, -, _ ]"
                               oninput="checkUserInput(this)"
                               onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                               value="&nbsp <c:out value='${person.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th class="active">&nbsp Surname:</th>
                    <td class="active">
                        <!--Checking length size and creating pop-up message-->
                        <!--Restrict input disallowed  symbols-->
                        <input type="text" name="surname" size="45" pattern=".{1,150}" required
                               title="1 to 150 characters : [ letters, numbers, -, _ ]"
                               oninput="checkUserInput(this)"
                               onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                               value="&nbsp <c:out value='${person.surname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th class="active">&nbsp Middlename:</th>
                    <td class="active">
                        <!--Checking length size and creating pop-up message-->
                        <!--Restrict input disallowed  symbols-->
                        <input type="text" name="middlename" size="45" pattern=".{,150}" required
                               title="up to 150 characters : [ letters, numbers, -, _ ]"
                               oninput="checkUserInput(this)"
                               onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                               value="&nbsp <c:out value='${person.middlename}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" button type="submit" class="btn btn-primary btn-lg" value="Add Person"
                               id="save"
                               style="float:right;margin-right:48%"/>

                        <br>
                        <button type="button" class="btn btn-link" onclick="window.location.href='list'"
                                style="float:right;margin-right:47%">
                            <span class="glyphicon glyphicon-user"></span>
                            Back to person list
                        </button>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
