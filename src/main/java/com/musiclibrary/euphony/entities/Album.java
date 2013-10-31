package com.musiclibrary.euphony.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * Album entity.
 * 
 * @author Branislav Novotny
 */
@Entity
public class Album implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String comment;
    
    private String title;
    
    private String cover;
    
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime releaseDate;
        
    @OneToMany(cascade = CascadeType.ALL)
    private List<Song> songs;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artist> artists;

    public Album() {
    }
    
    public Album(String title){
        this.title=title;
    }
    
    public Album(String title, String cover, DateTime releaseDate, List<Song> songs, String comment) {
        this.title = title;
        this.cover = cover;
        this.releaseDate = releaseDate;
        this.songs = songs;
        this.comment = comment;
        for (Song s : songs) {
            genres.add(s.getGenre());
            artists.add(s.getArtist());
        }
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public List<Genre> getGenres() {
        return genres;
    }

    public List<Artist> getArtists() {
        return artists;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Album other = (Album) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", title=" + title + ", cover=" + cover + ", releaseDate=" + releaseDate + ", songs=" + songs + '}';
    }
    
}
