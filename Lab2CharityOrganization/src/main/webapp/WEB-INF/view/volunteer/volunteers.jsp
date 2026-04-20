<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="ua.kpi.charity.utils.AttributesHolder" %>
<%@ page import="ua.kpi.charity.utils.PathsHolder" %>
<html>
<head>
    <title>Волонтери</title>
</head>
<body>
<%@include file="../header.jsp"%>

<div class="container mt-4">

    <div class="text-center mb-4">
        <h2>Реєстрація Волонтерів на участь у Заходах</h2>
    </div>

    <div class="mb-3">
        <a href="${PathsHolder.ADD_VOLUNTEER}">
            <button class="btn btn-primary">Додати Волонтера</button>
        </a>
    </div>

    <table class="table table-striped table-hover">
        <caption class="caption-top">Список Волонтерів</caption>
        <thead class="table-dark">
            <tr>
                <th>Ідентифікатор</th>
                <th>Ім`я</th>
                <th>Прізвище</th>
                <th>Номер телефону</th>
                <th>Ідентифікатор Заходу</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="volunteer" items="${requestScope[AttributesHolder.VOLUNTEERS]}">
            <tr>
                <input type="hidden" value="${volunteer.id}" name="${AttributesHolder.ID}">

                <td><c:out value="${volunteer.id}"/></td>
                <td><c:out value="${volunteer.firstName}"/></td>
                <td><c:out value="${volunteer.lastName}"/></td>
                <td><c:out value="${volunteer.phoneNumber}"/></td>
                <td><c:out value="${volunteer.event.id}"/></td>

                <td>
                    <a href="${PathsHolder.EDIT_VOLUNTEER}/${volunteer.id}" class="btn btn-warning btn-sm w-100">
                        Редагувати
                    </a>
                </td>
                <td>
                    <form action="${PathsHolder.DELETE_VOLUNTEER}/${volunteer.id}" method="post" style="margin: 0;">
                        <button type="submit" class="btn btn-danger btn-sm w-100">Видалити</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>