<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="contacts">
    <h2>Contact</h2>

    <table id="contactTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Type</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contact.contactList}" var="vet">
            <tr>
                <td>
                    <c:out value="${contact.name}"/>
                </td>
                <td>
                  
                  	<c:out value="${contact.type} "/>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
