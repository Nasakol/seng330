<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="myEvents">
    <h2>
        <c:if test="${event['new']}">New </c:if> Event
    </h2>
    <form:form modelAttribute="event" class="form-horizontal" id="add-event-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="name" name="name"/>
			
            <petclinic:inputField label="Address" name="address"/>
            <petclinic:inputField label="City" name="city"/>
            <petclinic:inputField label="Telephone" name="telephone"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${event['new']}">
                        <button class="btn btn-default" type="submit">Add Event</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Event</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
