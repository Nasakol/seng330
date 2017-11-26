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
package org.springframework.samples.petclinic.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Customer;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;

@Repository
public class JpaEmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public Collection<Employee> findAll() {
        Query query = this.em.createQuery("SELECT employee FROM Employee e left join fetch employee.employeeShifts");
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        Query query = this.em.createQuery("SELECT employee FROM Employee e left join fetch employee.employeeShifts WHERE employee.id =:id");
        query.setParameter("id", id);
        return (Employee) query.getSingleResult();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Employee> findByRole(String role) {
        Query query = this.em.createQuery("SELECT employee FROM Employee e left join fetch employee.employeeShifts WHERE employee.role =:role");
        query.setParameter("role", role);
        return query.getResultList();
    }
    
    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            this.em.persist(employee);
        } else {
            this.em.merge(employee);
        }

    }

	@Override
	public Employee findByEmail(String email) throws DataAccessException {
		Query query = this.em.createQuery("SELECT employee FROM Employee e left join fetch employee.employeeShifts WHERE employee.email =:email");
        query.setParameter("email", email);
        return (Employee) query.getSingleResult();
	}

	@Override
    @SuppressWarnings("unchecked")
    public Collection<Employee> findByLastName(String name) {
        Query query = this.em.createQuery("SELECT employee FROM Employee e left join fetch employee.employeeShifts WHERE employee.name =:name");
        query.setParameter("name", name);
        return query.getResultList();
    }

}
