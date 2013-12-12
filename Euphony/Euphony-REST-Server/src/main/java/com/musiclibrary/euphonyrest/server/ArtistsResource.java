/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyrest.server;

import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.ArtistDAO;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sebastian
 */
@Path("/artists")
@Component
public class ArtistsResource {
    @Autowired
    ArtistService artistService;
    
    @Context
    private UriInfo context;
        
    @GET
    @Path("{id}")
    @Produces("text/plain")
    public String getAll() {
        //VRATI ARTISTU
        if(artistService==null) return "NEFUNGUJE";
        return "IDE!";
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("id") Integer id, ArtistDAO artist) {
    
        System.out.println("----  putting item ");
 
        URI uri = context.getAbsolutePath();
        System.out.println(context.getAbsolutePath());
 
        /*
        Response response;
        if (!customerDB.containsKey(id)) {
            response = Response.created(uri).build();
        } else {
            response = Response.noContent().build();
        }
 
        customerDB.put(id, new CustomerResource(customerResource));
        return response;
        */
 
       return null;
    }
    
     
   @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        System.out.println("---- Deleting item nr. " + id);
        
        artistService.delete(null);
    }
}
