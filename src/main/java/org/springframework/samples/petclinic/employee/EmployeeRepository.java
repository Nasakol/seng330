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
package org.springframework.samples.petclinic.employee;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends Repository<Employee, Integer> {

    @Transactional(readOnly = true)
    Collection<Employee> findAll() throws DataAccessException;
    Employee findById(Integer employeeId) throws DataAccessException;
    Collection<Employee> findByPosition(String position) throws DataAccessException;
    
//    @Query("SELECT employee FROM Employee e WHERE e.lastName LIKE :lastName%")
//    @Transactional(readOnly = true)
    Collection<Employee> findByLastName(@Param("lastName") String lastName) throws DataAccessException;

    void save(Employee employee);



}
