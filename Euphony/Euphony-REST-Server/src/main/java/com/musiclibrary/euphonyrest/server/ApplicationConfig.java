/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyrest.server;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Sebastian
 */
//@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
 
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.musiclibrary.euphonyrest.server.ArtistsResource.class);
    }
}
