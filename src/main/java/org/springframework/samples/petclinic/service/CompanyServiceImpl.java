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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.Customer;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeShift;
import org.springframework.samples.petclinic.repository.ContactRepository;
import org.springframework.samples.petclinic.repository.CustomerRepository;
import org.springframework.samples.petclinic.repository.EventRepository;
import org.springframework.samples.petclinic.repository.EmployeeRepository;
import org.springframework.samples.petclinic.repository.EmployeeShiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private EventRepository eventRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private EmployeeShiftRepository employeeShiftRepository;
    private ContactRepository contactRepository;

    @Autowired
    public CompanyServiceImpl(EventRepository petRepository, EmployeeRepository vetRepository, CustomerRepository ownerRepository, EmployeeShiftRepository visitRepository) {
        this.eventRepository = petRepository;
        this.employeeRepository = vetRepository;
        this.customerRepository = ownerRepository;
        this.employeeShiftRepository = visitRepository;
    }

	
	
	
	@Override
    @Transactional(readOnly = true)
	public Customer findCustomerById(int id) throws DataAccessException {
		 return customerRepository.findById(id);
	}

	@Override
    @Transactional(readOnly = true)
	public void saveCustomer(Customer customer) throws DataAccessException {
		customerRepository.save(customer);
		
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<Employee> findAllEmployee() throws DataAccessException {
		return employeeRepository.findAll();
	}

	@Override
    @Transactional(readOnly = true)
	public Employee findEmployeeById(int id) throws DataAccessException {
		return employeeRepository.findById(id);
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<Employee> findEmployeeByRole(String role) throws DataAccessException {
		return employeeRepository.findByRole(role);
	}

	@Override
    @Transactional(readOnly = true)
	public void saveEmployeeShift(EmployeeShift employeeShift) throws DataAccessException {
		employeeShiftRepository.save(employeeShift);
		
	}

	@Override
    @Transactional(readOnly = true)
	public Event findEventById(int id) throws DataAccessException {
		return eventRepository.findById(id);
	}

	@Override
    @Transactional(readOnly = true)
	public List<Event> findEventByName(String name) throws DataAccessException {
		return eventRepository.findByName(name);
	}

	@Override
    @Transactional(readOnly = true)
	public void saveEvent(Event event) throws DataAccessException {
		eventRepository.save(event);
		
	}


	@Override
	public void saveEmployee(Employee employee) throws DataAccessException {
		employeeRepository.save(employee);
		
	}
	

	@Override
    @Transactional(readOnly = true)
	public Collection<Contact> findAllContact() throws DataAccessException {
		return contactRepository.findAll();
	}

	@Override
    @Transactional(readOnly = true)
	public Contact findContactById(int contactId) throws DataAccessException {
		return contactRepository.findById(contactId);
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<Contact> findContactByType(String type) throws DataAccessException {
		return contactRepository.findByType(type);
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<Contact> findContactByName(String name) throws DataAccessException {
		return contactRepository.findByName(name);
	}

	@Override
    @Transactional(readOnly = true)
	public void save(Contact contact) {
		contactRepository.save(contact);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findCustomerByEmail(String email) throws DataAccessException {
		return customerRepository.findByEmail(email);
	}


	@Override
	@Transactional(readOnly = true)
	public List<EmployeeShift> findEmployeeShiftByEmployeeId(int employeeId) throws DataAccessException {
		return employeeShiftRepository.findByEmployeeId(employeeId);
	}




	@Override
	public Employee findEmployeeByEmail(String email) throws DataAccessException {
		return employeeRepository.findByEmail(email);
	}


}
