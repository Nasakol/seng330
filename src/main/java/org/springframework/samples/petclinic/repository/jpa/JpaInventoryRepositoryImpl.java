package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Inventory;
import org.springframework.samples.petclinic.model.Inventory;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.EmployeeShift;
import org.springframework.samples.petclinic.repository.InventoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaInventoryRepositoryImpl implements InventoryRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Inventory> findAll()  throws DataAccessException{
        Query query = this.em.createQuery("SELECT inventory FROM Inventory e");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Inventory> findByType(String type)  throws DataAccessException{
        Query query = this.em.createQuery("SELECT inventory FROM Inventory e WHERE inventory.type =:type");
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Inventory>  findByName(String name)  throws DataAccessException{
        Query query = this.em.createQuery("SELECT inventory FROM Inventory e WHERE inventory.name =:name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void save(Inventory inventory)  throws DataAccessException{
        if (inventory.getId() == null) {
            this.em.persist(inventory);
        } else {
            this.em.merge(inventory);
        }
    }

}

