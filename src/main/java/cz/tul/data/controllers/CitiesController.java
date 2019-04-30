package cz.tul.data.controllers;

import cz.tul.data.City;
import cz.tul.data.Country;
import cz.tul.service.CityService;
import cz.tul.service.CountryService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitiesController {

    protected CityService cityService;
    protected CountryService countryService;

    protected Logger logger = LoggerFactory.getLogger(CitiesController.class);

    @Autowired
    public void setCityService(CityService cityService) { //lecture11_springMvc
        this.cityService = cityService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<City>> getCities() {
        this.logger.info("GET /cities");
        List<City> countries = this.cityService.selectAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @RequestMapping(value = "/cities/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<City> getCity(@PathVariable int id) {
        this.logger.info("GET /cities/" + id);
        try {
            City city = this.cityService.getById(id);
            if(city == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(city, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<City> createCity(@RequestBody City city) { //lecture12_RestOWn
        this.logger.info("POST /cities/\n" + city);
        try {
            if(this.cityService.getById(city.getId()) != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            if(this.countryService.getByCode(city.getCountry().getCode()) == null) {
               this.countryService.create(city.getCountry());
            }

            this.cityService.create(city);
            return new ResponseEntity<>(city, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/cities/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteCity(@PathVariable int id) {
        this.logger.info("DELETE /cities" + id);
        try {
            City city = this.cityService.getById(id);
            if(city == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            this.cityService.delete(city);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/cities/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<City> updateCity(@PathVariable int id, @RequestBody String httpBody) {
        this.logger.info("PUT /cities/" +  id + "\n" + httpBody);
        try {
            City city = this.cityService.getById(id);
            if(city == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            JSONObject rawData = new JSONObject(httpBody);
            if(rawData.has("name")) {
                city.setName(rawData.getString("name"));
            }

            if(rawData.has("country_code")) {
                Country country = this.countryService.getByCode(rawData.getString("country_code"));
                if(country != null) {
                    city.setCountry(country);
                }
            }

            if(rawData.has("openWeatherMapName")) {
                city.setOpenWeatherMapName(rawData.getString("openWeatherMapName"));
            }

            this.cityService.update(city);
            return new ResponseEntity<City>(city, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }







}
