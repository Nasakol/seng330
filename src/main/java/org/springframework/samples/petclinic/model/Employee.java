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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "employee")
public class Employee extends Person {


	@Column(name = "role")
    @NotEmpty
    private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<EmployeeShift> schedules;
	
	
	
	
	public String getPosition() {
		return role;
	}

	public void setPosition(String position) {
		this.role = position;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<EmployeeShift> employeeShifts;

	protected void setEmployeeShiftsInternal(Set<EmployeeShift> employeeShifts) {
        this.employeeShifts = employeeShifts;
    }

    protected Set<EmployeeShift> getEmployeeShiftsInternal() {
        if (this.employeeShifts == null) {
            this.employeeShifts = new HashSet<EmployeeShift>();
        }
        return this.employeeShifts;
    }

    public List<EmployeeShift> getEmployeeShifts() {
        List<EmployeeShift> sortedEmployeeShifts = new ArrayList<EmployeeShift>(getEmployeeShiftsInternal());
        PropertyComparator.sort(sortedEmployeeShifts, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedEmployeeShifts);
    }

    public void addEmployeeShift(EmployeeShift employeeShift) {
        getEmployeeShiftsInternal().add(employeeShift);
        employeeShift.setEmployee(this);
    }

}
