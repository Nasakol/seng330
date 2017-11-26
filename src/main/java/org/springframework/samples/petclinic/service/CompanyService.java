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
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.Customer;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeShift;


public interface CompanyService {

	Customer findCustomerByEmail(String lastName) throws DataAccessException;
    Customer findCustomerById(int id) throws DataAccessException;
    void saveCustomer(Customer customer) throws DataAccessException;
    
    Collection<Employee> findAllEmployee() throws DataAccessException;
	Employee findEmployeeById(int id) throws DataAccessException;
	Employee findEmployeeByEmail(String email) throws DataAccessException;
	Collection<Employee> findEmployeeByRole(String role) throws DataAccessException;
	void saveEmployee(Employee employee) throws DataAccessException;
	Collection<Employee> findEmployeeByLastName(String lastName);
	
	void saveEmployeeShift(EmployeeShift employeeShift) throws DataAccessException;
    List<EmployeeShift> findEmployeeShiftByEmployeeId(int employeeId) throws DataAccessException;
    
    
    Event findEventById(int id) throws DataAccessException;
	List<Event> findEventByName(String name) throws DataAccessException;
	void saveEvent(Event event) throws DataAccessException;
	
	 Collection<Contact> findAllContact() throws DataAccessException;
	 Contact findContactById(int contactId) throws DataAccessException;
	 Collection<Contact> findContactByType(String type) throws DataAccessException;
	 Collection<Contact> findContactByName(String name) throws DataAccessException;
	 void saveContact(Contact contact);
	Collection<Customer> findCustomerByLastName(String lastName);
	
	


}
