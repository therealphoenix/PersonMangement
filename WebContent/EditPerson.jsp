<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Bootstrap -->
    <link href = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel = "stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

    <!--[if lt IE 9]>
    <script src = "https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src = "https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

    <div class = "panel panel-default">
        <div class = "panel-heading">
            <span class = "label label-info">TAT</span>
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

            <table class = "table" >
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
                    <input type="hidden" name="id" value="<c:out value='${person.id}' />" />
                </c:if>
                <tr>
                    <th class="active">&nbsp Name: </th>
                    <td class="active">
                        <!--Restrict input disallowed  symbols-->
                        <input type="text" name="name" size="45"
                               oninput="checkUserInput(this)"
                               onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                               value="&nbsp <c:out value='${person.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th class="active">&nbsp Surname: </th>
                    <td class="active">
                        <!--Restrict input disallowed  symbols-->
                        <input type="text" name="surname" size="45"
                               oninput="checkUserInput(this)"
                               onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                               value="&nbsp <c:out value='${person.surname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th class="active">&nbsp Middlename: </th>
                    <td class="active">
                        <!--Restrict input disallowed  symbols-->
                        <input type="text" name="middlename" size="45"
                               oninput="checkUserInput(this)"
                               onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                               value="&nbsp <c:out value='${person.middlename}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th class="active">&nbsp Phone numbers:&nbsp </th>
                    <td class="active"> <c:forEach var="phone" items="${listPersonPhone}">
                        <c:out value=" ${phone.number}" />
                        <!--Align hrefs to right-->

                        <button type = "button"
                                class = "btn btn-warning" onclick="window.location.href='deletephone?id=<c:out value='${phone.id}' />'"
                                style="float:right;margin-right:43%" >Delete</button>
                        <button type = "button"
                        class = "btn btn-success" onclick="window.location.href='editphone?id=<c:out value='${phone.id}' />'"
                        style="float:right;margin-right:3%" >Edit</button>
                        <br>
                        <br>
                </c:forEach>
                        <button type = "button" class = "btn btn-info" onclick="window.location.href='newphone'">Add phone</button>
                        </td>
                </tr>
                    <td colspan="2" align="center">
                        <input type="submit" button type = "submit" class = "btn btn-primary btn-lg" value="Save Person" id = "save"/>
                        <br>
                        <button type = "button" class = "btn btn-link" onclick="window.location.href='list'">Back to person list</button>

                    </td>
                </tr>
            </table>
        </form>
</div>
<script>
    function checkUserInput (object)
    {
        if (this.ST) return;
        var overPlace = object.value;
        var overLoad = overPlace.replace (/^[а-яА-ЯёЁa-zA-Z]+$/, '').length; this.ST = true;
        if (overLoad > 0) {
            object.value = object.lang; showError (object);
            return
        }
        object.lang = object.value; this.ST = null;
    }

    function showError (object)
    {
        if (!this.OBJ)
        {
            this.OBJ = object; object.style.backgroundColor = 'pink';
            this.TIM = setTimeout (showError, 200)}
        else
        {
            this.OBJ.style.backgroundColor = '';
            clearTimeout (this.TIM); this.ST = null;
            checkUserInputPhoneNumber (this.OBJ);
            this.OBJ = null
        }
    }
</script>
</body>
</html>
