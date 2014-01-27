/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.AccountDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Brano
 */
@Repository
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public AccountDAOImpl() {
    }

    public AccountDAOImpl(EntityManager em) {
        this.em = em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Account entity) {
        try {
            Util.validateAccount(entity);

            if (entity.getId() != null) {
                throw new IllegalArgumentException("This account entity is already in databse.");
            }

            em.persist(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
    @Override
    public void update(Account entity) {
        try {
            Util.validateAccount(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This account entity cannot have null id.");
            }
            if (em.find(Account.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This account entity does not exist in database.");
            }

            em.merge(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void delete(Account entity) {
        try {
            Util.validateAccount(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This account entity cannot have null id.");
            }
            if (em.find(Account.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This account entity does not exist in database.");
            }

            Account objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Account getByUsername(String username) {
        try {
            if (username == null) {
                throw new IllegalArgumentException("Name cannot be null.");
            }

            Query q = em.createQuery("FROM Account WHERE username=:username");
            q.setParameter("username", username);
            try{
                Account acc = (Account) q.getSingleResult();
                return acc;
            }catch (NoResultException ex) {
                return null;
            }            
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
    @Override
    public Account getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Account objectTemp = (Account) em.find(Account.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

}
