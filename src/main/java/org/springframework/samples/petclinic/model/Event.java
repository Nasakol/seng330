/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event extends NamedEntity {

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "isComplete")
    private boolean isComplete;

    @NotEmpty
    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", fetch = FetchType.EAGER)
//    private Set<Visit> food;


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    protected void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
    public String getDescription() {
        return this.description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

//    protected Set<Food> getFoodInternal() {
//        if (this.foods == null) {
//            this.foods = new HashSet<>();
//        }
//        return this.foods;
//    }
//
//    protected void setVisitsInternal(Set<Foods> foods) {
//        this.foods = foods;
//    }
//
//    public List<Visit> getFoods() {
//        List<Visit> Foods = new ArrayList<>(getFoodsInternal());
//        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
//        return Collections.unmodifiableList(sortedFoods);
//    }
//
//    public void addFood(Food food) {
//        getFoodsInternal().add(food);
//        event.setEvent(this);
//    }

}
