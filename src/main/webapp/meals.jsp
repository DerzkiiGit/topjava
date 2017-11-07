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
<c:forEach var="meal" items="${mealsList}">
    <c:choose>
        <c:when test="${meal.exceed}">
            <h1 class="red">${meal.dateTime} ${meal.description} ${meal.calories}</h1>
        </c:when>
        <c:otherwise>
            <h1>${meal.dateTime} ${meal.description} ${meal.calories}</h1>
        </c:otherwise>
    </c:choose>
</c:forEach>


</body>
</html>