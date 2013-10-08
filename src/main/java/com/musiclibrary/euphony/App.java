package com.musiclibrary.euphony;

import com.musiclibrary.euphony.entities.Admin;
import javax.persistence.EntityManagerFactory;

/**
 * tajne heslo
 *
 */
public class App 
{
    public static String getCurrentNameInDb(EntityManagerFactory emf, Long id) {
        return emf.createEntityManager().find(Admin.class, id).getName();
    }
    
    public static void main( String[] args )
    {
        System.out.println( "velmi tajne heslo: tomas je pan" );
    }
}
