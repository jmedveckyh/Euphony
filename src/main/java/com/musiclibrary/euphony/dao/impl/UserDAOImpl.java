package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.UserDAO;
import com.musiclibrary.euphony.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Sebastian Lazon
 */
public class UserDAOImpl implements UserDAO{
    
    @PersistenceContext
    private EntityManager em;
    
    public void create(User user) {
        
        if (user == null) {
            throw new IllegalArgumentException("User entity cannot be null.");
        } 
        
        em.persist(user);
        
    }

    public void update(User user) {
        
        if (user == null) {
            throw new IllegalArgumentException("User entity cannot be null.");
        }
            
        em.merge(user);
        
    }

    public void delete(User user) {
        
        if (user == null) {
            throw new IllegalArgumentException("User entity cannot be null.");
        } 
        
        if (em.find(User.class, user.getId()) == null) {
            throw new IllegalArgumentException("This User entity does not exist in database.");
        }
        
        User userTemp = em.merge(user);
        
        em.remove(userTemp);
        
    }

    public User getById(Long id) {
        
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        
        User userTemp = em.find(User.class, id);
        
        return userTemp;
        
    }

}
