/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.services.AccountService;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;

/**
 *
 * @author Sebastian
 */
@DoesNotRequireLogin
@UrlBinding("/auth/{event}")
public class AuthActionBean extends BaseActionBean {

    @SpringBean
    protected AccountService accountService;
    @Validate(required = true, on = {"submitLogin", "submitRegister"})
    private String username;
    @Validate(required = true, on = {"submitLogin", "submitRegister"})
    private String password;

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

    public Resolution submitLogin() {
        HttpSession session = getContext().getRequest().getSession();
        String path = (String) session.getAttribute("userPath");
        AccountDTO adto = accountService.login(username, password);
        if (adto != null) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("admin", adto.getIsAdmin());
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
        acc.setPassword(password);
        acc.setUsername(username);

        AccountDTO accFromDb = accountService.register(acc);
        if (accFromDb != null) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("admin", false);
            return new ForwardResolution("index.jsp");
        } else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("register.error"));
            return new ForwardResolution("login.jsp");
        }
    }

    public Resolution logout() {
        getContext().getRequest().getSession().setAttribute("loggedIn", false);
        return new ForwardResolution("login.jsp");
    }
}
