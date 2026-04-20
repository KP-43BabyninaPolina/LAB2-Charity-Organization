<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="ua.kpi.charity.utils.AttributesHolder" %>
<%@ page import="ua.kpi.charity.utils.PathsHolder" %>
<html>
<head>
    <title>Додати/Редагувати Волонтера</title>
</head>
<body class="bg-light">
<%@include file="../header.jsp"%>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm">
                <div class="card-body p-4 p-md-5">
                    <form action="" method="post">

                        <h2 class="text-center mb-4">
                            <c:choose>
                                <c:when test="${requestScope[AttributesHolder.VOLUNTEER] != null && !requestScope[AttributesHolder.NEW_MODE]}">
                                    Редагування Волонтера
                                </c:when>
                                <c:otherwise>
                                    Додавання Волонтера
                                </c:otherwise>
                            </c:choose>
                        </h2>

                        <c:if test="${requestScope[AttributesHolder.ERROR_MESSAGE] != null}">
                            <div class="alert alert-danger">
                                    ${requestScope[AttributesHolder.ERROR_MESSAGE]}
                            </div>
                        </c:if>

                        <input type="hidden" value="${volunteer.id}" name="${AttributesHolder.ID}">

                        <div class="mb-3">
                            <c:if test="${errors != null && errors.messages[AttributesHolder.FIRST_NAME] != null}">
                                <div class="text-danger small mb-1">
                                        ${errors.messages[AttributesHolder.FIRST_NAME]}
                                </div>
                            </c:if>
                            <input type="text" class="form-control" name="${AttributesHolder.FIRST_NAME}"
                                   value="<c:out value="${volunteer.firstName}"/>" placeholder="Ім'я" required>
                        </div>

                        <div class="mb-3">
                            <c:if test="${errors != null && errors.messages[AttributesHolder.LAST_NAME] != null}">
                                <div class="text-danger small mb-1">
                                        ${errors.messages[AttributesHolder.LAST_NAME]}
                                </div>
                            </c:if>
                            <input type="text" class="form-control" name="${AttributesHolder.LAST_NAME}"
                                   value="<c:out value="${volunteer.lastName}"/>" placeholder="Прізвище" required>
                        </div>

                        <div class="mb-3">
                            <c:if test="${errors != null && errors.messages[AttributesHolder.PHONE_NUMBER] != null}">
                                <div class="text-danger small mb-1">
                                        ${errors.messages[AttributesHolder.PHONE_NUMBER]}
                                </div>
                            </c:if>
                            <input type="text" class="form-control" name="${AttributesHolder.PHONE_NUMBER}"
                                   value="<c:out value="${volunteer.phoneNumber}"/>" placeholder="Номер телефону" required>
                        </div>

                        <div class="mb-3">
                            <c:if test="${errors != null && errors.messages[AttributesHolder.BIRTH_DATE] != null}">
                                <div class="text-danger small mb-1">
                                        ${errors.messages[AttributesHolder.BIRTH_DATE]}
                                </div>
                            </c:if>
                            <label for="birthDate" class="text-muted small mb-1" style="padding-left: 12px;">Дата народження</label>
                            <input type="date" class="form-control" id="birthDate" name="${AttributesHolder.BIRTH_DATE}"
                                   value="<c:out value="${volunteer.birthDate}"/>" required>
                        </div>

                        <div class="mb-4">
                            <c:if test="${errors != null && errors.messages[AttributesHolder.EVENT] != null}">
                                <div class="text-danger small mb-1">
                                        ${errors.messages[AttributesHolder.EVENT]}
                                </div>
                            </c:if>
                            <select class="form-select" name="${AttributesHolder.EVENT}" id="eventSelect" required>
                                <option value="">Оберіть Захід</option>
                                <c:forEach var="item" items="${requestScope[AttributesHolder.EVENTS]}">

                                    <c:set var="statusText" value="${item.status}" />
                                    <c:choose>
                                        <c:when test="${item.status == 'SUM_GOAL_REACHED'}">
                                            <c:set var="statusText" value="Профінансовано" />
                                        </c:when>
                                        <c:when test="${item.status == 'PLANNED'}">
                                            <c:set var="statusText" value="Заплановано" />
                                        </c:when>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${requestScope[AttributesHolder.VOLUNTEER] != null &&
                                                requestScope[AttributesHolder.VOLUNTEER].event.id == item.id}">
                                            <option selected value="${item.id}">
                                                ${item.id} - [${statusText}] - ${item.name}
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.id}">
                                                ${item.id} - [${statusText}] - ${item.name}
                                            </option>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>
                            </select>
                        </div>

                        <button class="btn btn-lg btn-primary w-100" type="submit">
                            Зберегти
                        </button>

                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>