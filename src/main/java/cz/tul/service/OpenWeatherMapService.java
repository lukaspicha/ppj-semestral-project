package cz.tul.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tul.data.Measurement;
import org.omg.CORBA.Object;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

public class OpenWeatherMapService {

    //TODO nacist si to z app.properties
    protected String apiUrl = "https://api.openweathermap.org/data/2.5/weather";
    protected String appId = "685d749db94d50fddfc47e2f8f28e1a8";
    protected String units = "metric";

    protected RestTemplate restTemplate;

    protected UriComponentsBuilder uriBuilder;

    public OpenWeatherMapService() {

        this.uriBuilder = UriComponentsBuilder.fromUriString(this.apiUrl);
        this.uriBuilder.queryParam("appId", this.appId);
        this.uriBuilder.queryParam("units", this.units);
        this.restTemplate = new RestTemplate();

    }

    //TODO upravit, resp.pro kazde nove volani resetovat predchozi cityid
    public Measurement getByCityId(String cityId) {
        this.uriBuilder.queryParam("id", cityId);
        try {
            ResponseEntity<String> response = this.restTemplate.getForEntity(uriBuilder.toUriString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode main = root.get("main");

             return new Measurement(main.get("temp").floatValue(), main.get("pressure").asInt(), main.get("humidity").asInt(), root.get("dt").asLong(), this.units);

        } catch (HttpStatusCodeException e) {
            int statusCode = e.getStatusCode().value();
            //return e.getMessage();
        } catch (IOException e) {
            //return e.getMessage();
        }

        return null;

    }




}
