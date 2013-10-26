package com.musiclibrary.euphony.util;

/**
 *
 * @author Medo
 */
public class DataAccessException extends Exception {

    public DataAccessException() {
    }
    
    public DataAccessException(String msg) {
        super(msg);
    }

    public DataAccessException(Throwable t) {
        super(t);
    }
    
    public DataAccessException(String msg, Throwable t) {
        super(msg, t);
    }
}
