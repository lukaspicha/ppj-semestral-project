package cz.tul.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import cz.tul.data.City;
import cz.tul.data.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenWeatherMapService {

    @Value("${openWeatherMap.apiUrl}")
    protected String apiUrl = "https://api.openweathermap.org/data/2.5/group";

    @Value("${openWeatherMap.appid}")
    protected String appId = "685d749db94d50fddfc47e2f8f28e1a8";

    @Value("${openWeatherMap.units}")
    protected String units = "metrics";

    protected RestTemplate restTemplate;

    protected UriComponentsBuilder uriBuilder;



    public OpenWeatherMapService() {
        this.uriBuilder = UriComponentsBuilder.fromUriString(this.apiUrl);
        this.uriBuilder.queryParam("appId", this.appId);
        this.uriBuilder.queryParam("units", this.units);
        this.restTemplate = new RestTemplate();
    }


    public List<Measurement> downloadWeatherDataByCitiesIds(List<String> citiesIds) {
        List<Measurement> measurements = new ArrayList<Measurement>();
        try {
            this.uriBuilder.queryParam("id",  String.join(",", citiesIds));
            ResponseEntity<String> response = this.restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

            if(response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(response.getBody());

                if(jsonResponse.get("cnt").intValue() > 0 && jsonResponse.get("list").isArray()) {
                    for (JsonNode cityMeasData : jsonResponse.get("list")) {
                        JsonNode cityMeasDataMain = cityMeasData.get("main");
                        Measurement meas = new Measurement(cityMeasData.get("id").asText(), cityMeasDataMain.get("temp").floatValue(), cityMeasDataMain.get("pressure").asInt(),  cityMeasDataMain.get("humidity").asInt(), cityMeasData.get("dt").asLong(), this.units);
                        measurements.add(meas);
                    }
                }
                //LOG

            } else {

               //LOG
            }
        } catch (HttpStatusCodeException e) {
            int statusCode = e.getStatusCode().value();
            System.out.println(e.getStatusText());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return measurements;

    }




}
