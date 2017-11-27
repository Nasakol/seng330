package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.EmployeeShift;
import org.springframework.samples.petclinic.repository.ContactRepository;
import org.springframework.transaction.annotation.Transactional;

public class JpaContactRepositoryImpl implements ContactRepository{
	 
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Contact> findAll()  throws DataAccessException{
        Query query = this.em.createQuery("SELECT contact FROM Contact e");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Contact> findByType(String type)  throws DataAccessException{
        Query query = this.em.createQuery("SELECT contact FROM Contact e WHERE contact.type =:type");
        query.setParameter("type", type);
        return query.getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Contact>  findByName(String name)  throws DataAccessException{
        Query query = this.em.createQuery("SELECT contact FROM Contact e WHERE contact.name =:name");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    @Override
    public void save(Contact contact)  throws DataAccessException{
        if (contact.getId() == null) {
            this.em.persist(contact);
        } else {
            this.em.merge(contact);
        }
    }

	@Override
	public Contact findById(int contactId) throws DataAccessException {
		return this.em.find(Contact.class, contactId);
	}

    

}
