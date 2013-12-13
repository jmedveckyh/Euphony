package com.musiclibrary.euphonyweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.StripesConstants;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import net.sourceforge.stripes.validation.LocalizableError;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public class DatabaseExceptionHandler extends DefaultExceptionHandler {

    public Resolution handleDatabaseException(DataAccessException exc, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);

        if (bean.getClass().equals(AlbumActionBean.class)) {
            bean.getContext().getValidationErrors().addGlobalError(new LocalizableError("validation.assignedalbum"));
            return new ForwardResolution("/album/list.jsp");
        }
        if (bean.getClass().equals(ArtistActionBean.class)) {
            bean.getContext().getValidationErrors().addGlobalError(new LocalizableError("validation.assignedartist"));
            return new ForwardResolution("/artist/list.jsp");
        }
        if (bean.getClass().equals(GenreActionBean.class)) {
            bean.getContext().getValidationErrors().addGlobalError(new LocalizableError("validation.assignedgenre"));
            return new ForwardResolution("/genre/list.jsp");
        }
        return null;
    }

    public Resolution handleIllegalArgumentGeneric(IllegalArgumentException exc, HttpServletRequest request, HttpServletResponse response) {

        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);

        if (bean.getClass().equals(Song2PlaylistActionBean.class)) {
            bean.getContext().getValidationErrors().addGlobalError(new LocalizableError("validation.assignedsonginplaylist"));
            return new ForwardResolution("/explore/songs.jsp");
        }

        return new ForwardResolution("/");
    }

    public Resolution handleIllegalNullpointerInPlaylist(NullPointerException exc, HttpServletRequest request, HttpServletResponse response) {

        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);

        if (bean.getClass().equals(Song2PlaylistActionBean.class)) {
            bean.getContext().getValidationErrors().addGlobalError(new LocalizableError("validation.noplaylistorsongselected"));
            return new ForwardResolution("/explore/songs.jsp");
        }

        return new ForwardResolution("/");
    }

    public Resolution handleGeneric(Exception exc, HttpServletRequest request, HttpServletResponse response) {
        return new ForwardResolution("/");
    }
}
