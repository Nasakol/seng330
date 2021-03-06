<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="newEmployee">
    <h2>
        <c:if test="${employee['new']}">New </c:if> Employee
    </h2>
    <form:form modelAttribute="employee" class="form-horizontal" id="add-employee-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="firstName" name="firstName"/>
            <petclinic:inputField label="lastName" name="lastName"/>
            <petclinic:inputField label="address" name="address"/>
            <petclinic:inputField label="email" name="email"/>
            <petclinic:inputField label="city" name="city"/>
            <petclinic:inputField label="telephone" name="telephone"/>
            <petclinic:inputField label="role" name="role"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${employee['new']}">
                        <button class="btn btn-default" type="submit">Add Employee</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Employee</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
