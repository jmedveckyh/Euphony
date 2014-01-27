/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyapi.dto;

import java.util.List;

/**
 *
 * @author Brano
 */
public class AccountDTO {

    private Long id;
    private String username;
    private String password;
    private Boolean isAdmin;
    private List<PlaylistDTO> playlists;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String username, String password, Boolean isAdmin, List<PlaylistDTO> playlists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.playlists = playlists;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    } 
}
