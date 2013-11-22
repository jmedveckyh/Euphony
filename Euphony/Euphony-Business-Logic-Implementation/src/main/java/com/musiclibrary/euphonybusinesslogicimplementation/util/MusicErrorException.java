package com.musiclibrary.euphonybusinesslogicimplementation.util;

/**
 * Custom exception for facade layer.
 * 
 * @author Tomas Smetanka #396209
 */
public class MusicErrorException extends RuntimeException {

    public MusicErrorException() {
    }

    public MusicErrorException(String message) {
        super(message);
    }

    public MusicErrorException(Throwable cause) {
        super(cause);
    }

    public MusicErrorException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
