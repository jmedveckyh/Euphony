package com.musiclibrary.euphony.dao;

import java.io.Serializable;


/**
 * Generic CRUD DAO interface
 * 
 * @author Tomáš Smetanka
 * 
 * @param <T> type of the entity
 */
public interface DAO<T> {
    /**
     * Creates a new entity T in the database.
     * 
     * @param entity T to be added to the database. Cannot be null.
     * @throws IllegalArgumentException if argument is null
     */
    public void create(T entity);

    /**
     * Updates an entity T in the database.
     * 
     * @param entity T to be added to the database. Cannot be null.
     * @throws IllegalArgumentException if the argument is null or the entity does not exist.
     */
    public void update(T entity);

    /**
     * Delete the entity T from the database.
     * 
     * @param entity T to be deleted. Cannot be null.
     * @throws IllegalArgumentException if the argument is null.
     */
    public void delete(T entity);
    
    /**
     * Returns entity T from the database.
     * 
     * @param cls Class of the entity T. Cannot be null.
     * @param id Id of the entity T. Cannot be null.
     * @return entity T with the specified id or null when it does not exist.
     * @throws IllegalArgumentException if the argument is null.
     */
    public T getById(Class cls, Long id);

}