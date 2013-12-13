package com.musiclibrary.euphonyrest.client;

import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * An action bean for manipulation with artist entity via REST api
 *
 * @author Medo
 */
@UrlBinding("/jsp/artists")
public class ArtistActionBean extends BaseActionBean {

    final static String URI = "http://localhost:8080/pa165/rest";
    @SpringBean
    RestTemplate restTemplate;

    public HttpHeaders getHttpHeader() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization", "Basic rest:rest");
        return requestHeaders;
    }

    @DefaultHandler
    public Resolution artists() {
        return new ForwardResolution("/jsp/artists.jsp");
    }

    public List<ArtistDTO> getArtists() {
        ArtistDTO[] artists = restTemplate.getForObject(URI + "/artists", ArtistDTO[].class);
        return (Arrays.asList(artists));
    }
    @Validate(on = {"add", "save"}, field = "name", required = true)
    private ArtistDTO artist;

    public Resolution add() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("event", "add");
        map.put("artist", artist);
        restTemplate.postForObject(URI, map, Map.class);
        return new RedirectResolution(this.getClass(), "artists");
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public Resolution delete() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("event", "delete");
        map.put("artist", artist);
        restTemplate.delete(URI + "/deleteArtist/" + artist.getId().toString());
        return new RedirectResolution(this.getClass(), "artists");
    }

    public Resolution edit() {
        return new ForwardResolution("/jsp/artistedit.jsp");
    }

    public Resolution save() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("event", "update");
        map.put("artist", artist);
        restTemplate.postForObject(URI, map, Map.class);
        return new RedirectResolution(this.getClass(), "artists");
    }

    public Resolution storno() {
        return new RedirectResolution(this.getClass(), "artists");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "delete"})
    public void loadArtistFromServer() {
        String ids = this.getContext().getRequest().getParameter("artist.id");
        if (ids == null) {
            return;
        }
        ArtistDTO a = restTemplate.getForObject(URI + "/artist?id=" + ids, ArtistDTO.class);
        if (a == null) {
            return;
        }
        this.artist = a;
    }
}
