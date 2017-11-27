package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Food;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.EmployeeShift;
import org.springframework.samples.petclinic.repository.FoodRepository;
import org.springframework.transaction.annotation.Transactional;

public class JpaFoodRepositoryImpl implements FoodRepository{
	 
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Food> findAll()  throws DataAccessException{
        Query query = this.em.createQuery("SELECT food FROM Food e");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Food> findByType(String type)  throws DataAccessException{
        Query query = this.em.createQuery("SELECT food FROM Food e WHERE food.type =:type");
        query.setParameter("type", type);
        return query.getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Food>  findByName(String name)  throws DataAccessException{
        Query query = this.em.createQuery("SELECT food FROM Food e WHERE food.name =:name");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    @Override
    public void save(Food food)  throws DataAccessException{
        if (food.getId() == null) {
            this.em.persist(food);
        } else {
            this.em.merge(food);
        }
    }

}

