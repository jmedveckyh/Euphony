/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Sebastian
 */
@Intercepts({LifecycleStage.ActionBeanResolution})
public class AuthInterceptor implements Interceptor{
   
    /**
     * Checks if a user is logged in. When not user is redirected to login page.
     *
     * @param ctxt ExecutionContext
     * @return Resolution
     * @throws Exception
     */
    @Override
    public Resolution intercept(ExecutionContext ctxt) throws Exception {
        Resolution resolution = ctxt.proceed();
        if(ctxt.getActionBean().getClass().isAnnotationPresent(NotLoggedIn.class)) {
            return resolution;
        }
        if(isLoggedIn(ctxt.getActionBeanContext())) {
            return resolution;
        } else {
            return new RedirectResolution(AuthActionBean.class, "/auth/login");
        }
    }

    protected boolean isLoggedIn(ActionBeanContext ctxt) {
        Boolean loggedIn = (Boolean) ctxt.getRequest().getSession().getAttribute("loggedIn");
        if(loggedIn != null && loggedIn) {
            return true;
        } else {
            return false;
        }
    }
    
    protected boolean isAdmin(ActionBeanContext ctxt){
        Boolean admin = (Boolean) ctxt.getRequest().getSession().getAttribute("admin");
        if(admin != null && admin) {
            return true;
        } else {
            return false;
        }
    }
}

