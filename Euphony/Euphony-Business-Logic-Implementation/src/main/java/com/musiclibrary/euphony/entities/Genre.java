package com.musiclibrary.euphony.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Genre entity.
 * Represents a musical genre with its own id, name and is mapped to a song
 * with ManyToOne relationship because many songs can be of one genre.
 * 
 * @author Jakub Medvecky-Heretik #396373
 */
@Entity
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    @ManyToOne
    private Song song;

    public Genre() {
    }

    public Genre(String name) {
        this.id = null;
        this.name = name;
    }
    
    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Genre other = (Genre) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name=" + name + '}';
    }
    
}
