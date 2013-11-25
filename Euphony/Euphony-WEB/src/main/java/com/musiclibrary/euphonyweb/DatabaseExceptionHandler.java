/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.StripesConstants;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public class DatabaseExceptionHandler extends DefaultExceptionHandler {

    public Resolution handleDatabaseException(DataAccessException exc, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);

        if (bean.getClass().equals(AlbumActionBean.class)) {
            ValidationErrors errors = new ValidationErrors();
            errors.addGlobalError(new SimpleError("Cannot delete album because it is assigned to some song."));
            bean.getContext().setValidationErrors(errors);
            return bean.getContext().getSourcePageResolution();
        }
        if (bean.getClass().equals(ArtistActionBean.class)) {
            ValidationErrors errors = new ValidationErrors();
            errors.addGlobalError(new SimpleError("Cannot delete artist because it is assigned to some song."));
            bean.getContext().setValidationErrors(errors);
            return bean.getContext().getSourcePageResolution();
        }
        if (bean.getClass().equals(GenreActionBean.class)) {
            ValidationErrors errors = new ValidationErrors();
            errors.addGlobalError(new SimpleError("Cannot delete genre because it is assigned to some song."));
            bean.getContext().setValidationErrors(errors);
            return bean.getContext().getSourcePageResolution();
        }
        return null;
    }

    public Resolution handleGeneric(Exception exc, HttpServletRequest request, HttpServletResponse response) {
        return new ForwardResolution("/");
    }
}