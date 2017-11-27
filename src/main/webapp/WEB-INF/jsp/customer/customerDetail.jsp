<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="customers">

    <h2>Customer Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${customer.firstName} ${customer.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><c:out value="${customer.address}"/></td>
        </tr>
        <tr>
            <th>City</th>
            <td><c:out value="${customer.city}"/></td>
        </tr>
        <tr>
            <th>City</th>
            <td><Email value="${customer.email}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${customer.telephone}"/></td>
        </tr>
    </table>

    <spring:url value="{customerId}/edit.html" var="editUrl">
        <spring:param name="customerId" value="${customer.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit My Profile</a>

    <spring:url value="{customerId}/events/new.html" var="addUrl">
        <spring:param name="customerId" value="${customer.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Event</a>

    <br/>
    <br/>
    <br/>
    <h2>Events</h2>

    <table class="table table-striped">
        <c:forEach var="event" items="${customer.events}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${event.name}"/></dd>
                        <dt>Event Date</dt>
                        <dd><fmt:formatDate value="${event.date}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${event.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                     
                        <tr>
                            <td>
                                <spring:url value="/customers/{customerId}/events/{eventId}/edit" var="eventUrl">
                                    <spring:param name="customerId" value="${customer.id}"/>
                                    <spring:param name="eventId" value="${event.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(eventUrl)}">Edit Event</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>

</petclinic:layout>
