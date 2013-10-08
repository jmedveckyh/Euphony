package com.musiclibrary.euphony.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * 
 * @author Tomas Smetanka #396209
 */

@Entity
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;

    public Admin() {
        this.id = null;
        this.name = null;
    }

    public Admin(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    } 
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
                return false;
        }
        if (getClass() != obj.getClass()) {
                return false;
        }
        final Admin other = (Admin) obj;
        return !(this.id == null || !this.id.equals(other.id));
    }
    
}
