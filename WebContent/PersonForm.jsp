<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<name>Phonebook Application</name>
</head>
<body>
	<center>
		<h1>Phonebook</h1>
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
						   oninput="checkUserInput(this)"
						   onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                			value="<c:out value='${person.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Surname: </th>
                <td>
                	<input type="text" name="surname" size="45"
						   oninput="checkUserInput(this)"
						   onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                			value="<c:out value='${person.surname}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Middlename: </th>
                <td>
                	<input type="text" name="middlename" size="45"
						   oninput="checkUserInput(this)"
						   onpropertychange="if ('v' == '\v' && parseFloat (navigator.userAgent.split ('MSIE ') [1].split (';') [0]) <= 9) Ftest (this)"
                			value="<c:out value='${person.middlename}' />"
                	/>
                </td>
            </tr>
			<tr>

			</tr>
            <tr>
            	<td colspan="2" align="center">

					<input type="submit"  value="Add person" />
					<form action="update" method="post">
					<input type="submit"  value="Save and Edit" />
					</form>
					<br>
					<a href="list">Back to person list</a>
            	</td>

            </tr>
        </table>
        </form>
    </div>
	<script>
		function checkUserInput (object)
		{
			if (this.ST) return; var overPlace = object.value;
			var overLoad = overPlace.replace (/^[а-яА-ЯёЁa-zA-Z]+$/, '').length; this.ST = true;
			if (overLoad > 0) {
				object.value = object.lang; showError (object); return
			}
			object.lang = object.value; this.ST = null;
		}

		function showError (object)
		{
			if (!this.OBJ)
			{
				this.OBJ = object; object.style.backgroundColor = 'pink'; this.TIM = setTimeout (showError, 200)}
			else
			{
				this.OBJ.style.backgroundColor = ''; clearTimeout (this.TIM); this.ST = null;
				checkUserInputPhoneNumber (this.OBJ); this.OBJ = null
			}
		}
	</script>
</body>
</html>
