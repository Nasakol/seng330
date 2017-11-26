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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.repository.EventRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEventRepositoryImpl implements EventRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> findByName(String name) {
    		return this.em.createQuery("SELECT distinct event FROM Event e WHERE event.name LIKE :name").getResultList();
    }

    @Override
    public Event findById(int id) {
        return this.em.find(Event.class, id);
    }

    @Override
    public void save(Event event) {
        if (event.getId() == null) {
            this.em.persist(event);
        } else {
            this.em.merge(event);
        }
    }

}
