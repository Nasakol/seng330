<%@ page session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="newCustomer">
    <h2>
        <c:if test="${customer['new']}">New </c:if> Customer - <c:out value="${sessionScope.name}"/>
    </h2>
    <form:form modelAttribute="customer" class="form-horizontal" id="add-customer-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="firstName" name="firstName"/>
            <petclinic:inputField label="lastName" name="lastName"/>
            <petclinic:inputField label="address" name="address"/>
            <petclinic:inputField label="email" name="email"/>
            <petclinic:inputField label="city" name="city"/>
            <petclinic:inputField label="telephone" name="telephone"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${customer['new']}">
                        <button class="btn btn-default" type="submit">Add Customer</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Customer</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
