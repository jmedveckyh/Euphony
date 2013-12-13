package com.musiclibrary.euphonyrest.client;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
import static com.musiclibrary.euphonyrest.client.GenreActionBean.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import org.springframework.web.client.RestTemplate;

/**
 * An action bean for manipulation with genre entity via REST api
 *
 * @author Medo
 */
@UrlBinding("/jsp/genres")
public class GenreActionBean extends BaseActionBean {

    @SpringBean
    RestTemplate restTemplate;

    @DefaultHandler
    public Resolution genres() {
        return new ForwardResolution("/jsp/genres.jsp");
    }

    public List<GenreDTO> getGenres() {
        GenreDTO[] genres = restTemplate.getForObject(URI + "/genres", GenreDTO[].class);
        return (Arrays.asList(genres));
    }
    @Validate(on = {"add", "save"}, field = "name", required = true)
    private GenreDTO genre;

    public Resolution add() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("event", "addGenre");
        map.put("genre", genre);
        restTemplate.postForObject(URI, map, Map.class);
        return new RedirectResolution(this.getClass(), "genres");
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public Resolution delete() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("event", "deleteGenre");
        map.put("genre", genre);
        restTemplate.delete(URI + "/deleteGenre/" + genre.getId().toString());
        return new RedirectResolution(this.getClass(), "genres");
    }

    public Resolution edit() {
        return new ForwardResolution("/jsp/genreedit.jsp");
    }

    public Resolution save() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("event", "updateGenre");
        map.put("genre", genre);
        restTemplate.postForObject(URI, map, Map.class);
        return new RedirectResolution(this.getClass(), "genres");
    }

    public Resolution storno() {
        return new RedirectResolution(this.getClass(), "genres");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "delete"})
    public void loadGenreFromServer() {
        String ids = this.getContext().getRequest().getParameter("genre.id");
        if (ids == null) {
            return;
        }
        GenreDTO g = restTemplate.getForObject(URI + "/genre?id=" + ids, GenreDTO.class);
        if (g == null) {
            return;
        }
        this.genre = g;
    }
}
