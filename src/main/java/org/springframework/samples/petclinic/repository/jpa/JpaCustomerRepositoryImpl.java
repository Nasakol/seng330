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

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.samples.petclinic.model.Customer;
import org.springframework.samples.petclinic.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaCustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;


    @SuppressWarnings("unchecked")
    public Collection<Customer> findByLastName(String lastName) {
        // using 'join fetch' because a single query should load both customers and events
        // using 'left join fetch' because it might happen that an customer does not have events yet
        Query query = this.em.createQuery("SELECT DISTINCT customer FROM Customer customer left join fetch customer.events WHERE customer.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

//    @Override
    public Customer findById(int id) {
        // using 'join fetch' because a single query should load both customers and events
        // using 'left join fetch' because it might happen that an customer does not have events yet
        Query query = this.em.createQuery("SELECT customer FROM Customer customer left join fetch customer.events WHERE customer.id =:id");
        query.setParameter("id", id);
        return (Customer) query.getSingleResult();
    }

//    @Override
    public Customer findByEmail(String email) {
        Query query = this.em.createQuery("SELECT customer FROM Customer customer left join fetch customer.events WHERE customer.id =:id");
        query.setParameter("email", email);
        return (Customer) query.getSingleResult();
    }


    @Override
    public void save(Customer customer) {
        if (customer.getId() == null) {
            this.em.persist(customer);
        } else {
            this.em.merge(customer);
        }

    }

}
