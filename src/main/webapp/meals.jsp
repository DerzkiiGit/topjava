<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Meals List</title>
</head>
<body>
<style type="text/css">
    .red{
        color: red;
    }

</style>

<c:out value="Meals List"/>
<table>
    <tr>
        <th width="150px">Date And Time</th>
        <th width="60px">Description</th>
        <th width="30px">Calories</th>
    </tr>
<c:forEach var="meal" items="${mealsList}">

    <c:choose>
        <c:when test="${meal.exceed}">
            <tr class="red">
            <td>${meal.dateTime.format(formatter)}</td>
             <td>${meal.description}</td>
             <td>${meal.calories}</td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <td>${meal.dateTime.format(formatter)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:otherwise>
    </c:choose>
</c:forEach>
</table>

</body>
</html>