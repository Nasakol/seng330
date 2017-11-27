package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.Food;
import org.springframework.transaction.annotation.Transactional;

public interface FoodRepository extends Repository<Food, Integer> {

  
   Collection<Food> findAll() throws DataAccessException;
   
   Collection<Food> findByType(String type) throws DataAccessException;
   
   Collection<Food> findByName(String name) throws DataAccessException;

   void save(Food food);



}

