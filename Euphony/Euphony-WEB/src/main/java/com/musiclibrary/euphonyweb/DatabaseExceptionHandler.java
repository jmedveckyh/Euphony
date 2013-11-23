/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;

/**
 *
 * @author Medo
 */
public class DatabaseExceptionHandler extends DefaultExceptionHandler {
    public Resolution handleDatabaseException(SQLException exc, HttpServletRequest request, HttpServletResponse response) {
        return new ForwardResolution("/album/list");
    }
    public Resolution handleGeneric(Exception exc, HttpServletRequest request, HttpServletResponse response) {
        return new ForwardResolution("/");
    }
}
