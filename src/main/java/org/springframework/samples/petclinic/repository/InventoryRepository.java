package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.Inventory;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryRepository extends Repository<Inventory, Integer> {

  
   Collection<Inventory> findAll() throws DataAccessException;
   
   Collection<Inventory> findByType(String type) throws DataAccessException;
   
   Collection<Inventory> findByName(String name) throws DataAccessException;

   void save(Inventory inventory);



}

