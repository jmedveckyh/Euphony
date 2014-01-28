package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.ValidationState;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 *
 * @author Sebastian
 */
@DoesNotRequireLogin
@UrlBinding("/auth/{event}")
public class AuthActionBean extends BaseActionBean {

    private static final String SALT="salt";
    @SpringBean
    protected MusicFacade musicFacade;
    @Validate(required = true, on = {"submitLogin", "submitRegister"})
    private String username;
    @Validate(required = true, minlength = 8, on = {"submitLogin", "submitRegister"})
    private String password;
    @Validate(required = true, minlength = 8, on = {"submitRegister"})
    private String passwordConfirm;
    @SpringBean
    PasswordEncoder passwordEncoder;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    @DefaultHandler
    public Resolution login() {
        return new ForwardResolution("login.jsp");
    }

    public Resolution register() {
        return new ForwardResolution("register.jsp");
    }

    public Resolution submitLogin() {
        HttpSession session = getContext().getRequest().getSession();
        AccountDTO adto = musicFacade.login(username, passwordEncoder.encodePassword(password, SALT));
        if (adto != null) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("admin", adto.getIsAdmin());
            session.setAttribute("username", adto.getUsername());
            return new ForwardResolution("index.jsp");
        } else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("login.error"));
            return new ForwardResolution("login.jsp");
        }
    }

    public Resolution submitRegister() {
        HttpSession session = getContext().getRequest().getSession();
        AccountDTO acc = new AccountDTO();
        acc.setIsAdmin(false);
        acc.setPassword(passwordEncoder.encodePassword(password, SALT));
        acc.setUsername(username);
        acc.setPlaylists(new ArrayList<PlaylistDTO>());

        AccountDTO accFromDb = musicFacade.register(acc);
        if (accFromDb != null) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("admin", false);
            session.setAttribute("username", username);
            return new ForwardResolution("index.jsp");
        } else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("register.error"));
            return new ForwardResolution("register.jsp");
        }
    }

    public Resolution logout() {
        getContext().getRequest().getSession().setAttribute("loggedIn", false);
        getContext().getRequest().getSession().setAttribute("admin", false);
        getContext().getRequest().getSession().invalidate();
        return new ForwardResolution("login.jsp");
    }

    @ValidationMethod(when = ValidationState.ALWAYS, on = {"submitRegister"})
    public void validatePass(ValidationErrors errors) {
        if (password == null || passwordConfirm == null) {
            return;
        }
        if (!password.equals(passwordConfirm)) {
            errors.add("cover", new LocalizableError("validation.passwordMatch"));
        }
    }
}
