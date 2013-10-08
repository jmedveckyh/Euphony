package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.AdminDAO;
import com.musiclibrary.euphony.entities.Admin;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Tomas Smetanka #396209
 */
//@Repository -- az po zakomponovani springu
public class AdminDAOImpl implements AdminDAO {

    @PersistenceContext
    private EntityManager em;

    public void create(Admin admin) {
        
        if (admin == null) {
            throw new IllegalArgumentException("Admin entity cannot be null.");
        } 
        
        em.persist(admin);
        
    }

    public void update(Admin admin) {
        
        if (admin == null) {
            throw new IllegalArgumentException("Admin entity cannot be null.");
        }
            
        em.merge(admin);
        
    }

    public void delete(Admin admin) {
        
        if (admin == null) {
            throw new IllegalArgumentException("Admin entity cannot be null.");
        } 
        
        if (em.find(Admin.class, admin.getId()) == null) {
            throw new IllegalArgumentException("This Admin entity does not exist in database.");
        }
        
        Admin adminTemp = em.merge(admin);
        
        em.remove(adminTemp);
        
    }

    public Admin getById(Long id) {
        
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        
        Admin adminTemp = em.find(Admin.class, id);
        
        return adminTemp;
        
    }
    
    
    

}
