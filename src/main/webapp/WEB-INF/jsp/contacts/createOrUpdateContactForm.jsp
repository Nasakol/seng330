<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="newContact">
    <h2>
        <c:if test="${contact['new']}">New </c:if> Contact
    </h2>
    <form:form modelAttribute="contact" class="form-horizontal" id="add-contact-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="name" name="name"/>
            <petclinic:inputField label="email" name="email"/>
            <petclinic:inputField label="phone" name="phone"/>
            <petclinic:inputField label="type" name="type"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${contact['new']}">
                        <button class="btn btn-default" type="submit">Add Contact</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Contact</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
