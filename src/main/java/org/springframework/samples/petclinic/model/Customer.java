package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "customer")
public class Customer extends Person {
   
    final private String role = "customer";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Event> events;
    
	public String getRole() {
		return role;
	}
	
	protected Set<Event> getEventsInternal() {
        if (this.events == null) {
            this.events = new HashSet<>();
        }
        return events;
    }

    protected void setPetsInternal(Set<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        List<Event> sortedEvents = new ArrayList<>(getEventsInternal());
        PropertyComparator.sort(sortedEvents, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedEvents);
    }

    public void addEvent(Event event) {
    		getEventsInternal().add(event);
        event.setCustomer(this);
    }

	
	@Override
    public String toString() {
        return new ToStringCreator(this)

            .append("id", this.getId())
            .append("new", this.isNew())
            .append("lastName", this.getLastName())
            .append("firstName", this.getFirstName())
            .append("address", this.address)
            .append("city", this.city)
            .append("telephone", this.telephone)
            .toString();
    }

}
