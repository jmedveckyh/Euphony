/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyrest.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.GenreService;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet for HTTP POST and GET methods allowing entity manipulation.
 *
 *
 */
@WebServlet(urlPatterns = "/rest/*")
public class EuphonyServlet extends HttpServlet {

    private ResourceBundle properties;
    @Autowired
    ArtistService artistService;
    @Autowired
    GenreService genreService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
        //properties = ResourceBundle.getBundle("Resources");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        /*if (artistService == null) {
         response.getOutputStream().print("NEFUNGUJE");
         } else {
         response.getOutputStream().print("FUNGUJE!!!");
         }
         */
        if (pathInfo != null) {
            if (pathInfo.matches("/")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
            if (pathInfo.matches("/artists")) {
                //response.getOutputStream().print("artists ");
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), artistService.getAll());
            } else if (pathInfo.matches("/genres")) {
                //response.getOutputStream().print("genres ");
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), genreService.getAll());
            } else if (pathInfo.matches("/artist")) {
                //response.getOutputStream().print("artist ");
                response.setContentType("application/json");
                Long id = Long.parseLong(request.getParameter("id"));
                if (id != null) {
                    ArtistDTO artist = artistService.getById(id);
                    if (artist != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.writeValue(response.getOutputStream(), artist);
                    } else {
                        response.sendError(404, properties.getString("error.nosuchartist")); 
                    }
                }
            } else if (pathInfo.matches("/genre")) {
                //response.getOutputStream().print("genre ");
                response.setContentType("application/json");
                Long id = Long.parseLong(request.getParameter("id"));
                if (id != null) {
                    GenreDTO genre = genreService.getById(id);
                    if (genre != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.writeValue(response.getOutputStream(), genre);
                    } else {
                        response.sendError(404, properties.getString("error.nosuchgenre"));
                    }
                }
            }
        }

    }

    @Override
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
