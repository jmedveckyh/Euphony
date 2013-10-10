package com.musiclibrary.euphony.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * 
 * 
 * @author Branislav Novotny
 */
@Entity
public class Album implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    
    private String cover;
    
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime releaseDate;
        
    @OneToOne
    private List<Song> songs;
    
    //artist-a nam netreba uz ne?

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public DateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(DateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    
    @Override
    public int hashCode() {
        return (this.id != null ? this.id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
}
