/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import net.sourceforge.stripes.localization.DefaultLocalePicker;

/**
 *
 * @author Sebastian
 */
public class LocalePicker extends DefaultLocalePicker {

    public Locale pickLocale(HttpServletRequest request) {
        if (request.getLocale().getLanguage() == "sk" || request.getLocale().getLanguage() == "cs") {
            return new Locale("sk");
        }
        return Locale.ENGLISH;
    }
}
