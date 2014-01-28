package com.musiclibrary.euphonyweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet for HTTP POST and GET methods allowing entity manipulation.
 *
 *
 */
@WebServlet(urlPatterns = "/server/*")
public class EuphonyServlet extends HttpServlet {

    private ResourceBundle properties;

    @Autowired
    MusicFacade musicFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
        properties = ResourceBundle.getBundle("Resources");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null) {
            if (pathInfo.matches("/")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
            if (pathInfo.matches("/artists")) {
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), musicFacade.getAllArtists());
            } else if (pathInfo.matches("/genres")) {
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), musicFacade.getAllGenres());
            } else if (pathInfo.matches("/artist")) {
                response.setContentType("application/json");
                Long id = Long.parseLong(request.getParameter("id"));
                if (id != null) {
                    ArtistDTO artist = musicFacade.getArtistById(id);
                    if (artist != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.writeValue(response.getOutputStream(), artist);
                    } else {
                        response.sendError(404, properties.getString("error.invalidartist"));
                    }
                }
            } else if (pathInfo.matches("/genre")) {
                response.setContentType("application/json");
                Long id = Long.parseLong(request.getParameter("id"));
                if (id != null) {
                    GenreDTO genre = musicFacade.getGenreById(id);
                    if (genre != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.writeValue(response.getOutputStream(), genre);
                    } else {
                        response.sendError(404, properties.getString("error.invalidgenre"));
                    }
                }
            }
        }

    }

    @Override
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(request.getInputStream(), HashMap.class);

        if (map.get("event").equals("updateArtist")) {
            ArtistDTO artist = mapper.convertValue(map.get("artist"), ArtistDTO.class);
            if (artist != null && musicFacade.getArtistById(artist.getId()) != null) {
                musicFacade.update(artist);
            } else {
                response.sendError(404, properties.getString("error.invalidartist"));
            }
        } else if (map.get("event").equals("updateGenre")) {
            GenreDTO genre = mapper.convertValue(map.get("genre"), GenreDTO.class);
            if (genre != null && musicFacade.getGenreById(genre.getId()) != null) {
                musicFacade.update(genre);
            } else {
                response.sendError(404, properties.getString("error.invalidgenre"));
            }
        } else if (map.get("event").equals("addArtist")) {
            ArtistDTO artist = mapper.convertValue(map.get("artist"), ArtistDTO.class);
            if (artist != null && artist.getId() == null) {
                musicFacade.create(artist);
            } else {
                response.sendError(404, properties.getString("error.invalidartist"));
            }
        } else if (map.get("event").equals("addGenre")) {
            GenreDTO genre = mapper.convertValue(map.get("genre"), GenreDTO.class);
            if (genre != null && genre.getId() == null) {
                musicFacade.create(genre);
            } else {
                response.sendError(404, properties.getString("error.invalidgenre"));
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, properties.getString("error.unknownrequest"));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            if (pathInfo.contains("/deleteArtist")) {
                Long id = Long.parseLong(pathInfo.substring(14));
                if (id != null && musicFacade.getArtistById(id) != null) {
                    try {
                        musicFacade.delete(musicFacade.getArtistById(id));
                    } catch (DataAccessException ex) {
                        response.sendError(404, properties.getString("error.assignedartist"));
                    }
                } else {
                    response.sendError(404, properties.getString("error.invalidartist"));
                }
            }
            if (pathInfo.contains("/deleteGenre")) {
                Long id = Long.parseLong(pathInfo.substring(13));
                if (id != null && musicFacade.getGenreById(id) != null) {
                    try {
                        musicFacade.delete(musicFacade.getGenreById(id));
                    } catch (DataAccessException ex) {
                        response.sendError(404, properties.getString("error.assignedgenre"));
                    }
                } else {
                    response.sendError(404, properties.getString("error.invalidgenre"));
                }
            }
        }

    }
}
