package cz.tul.data.controllers;

import cz.tul.data.Country;
import cz.tul.data.CountryNotFoundException;
import cz.tul.data.ResponseStatusException;
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
public class CountriesController {

    protected CountryService countryService;

    protected Logger logger = LoggerFactory.getLogger(CountriesController.class);

    @Autowired
    public void setCountryService(CountryService countryService) { //lecture11_springMvc
        this.countryService = countryService;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Country>> getCountries() {
        this.logger.info("GET /countries");
        List<Country> countries = this.countryService.selectAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @RequestMapping(value = "/countries/{code}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Country> getCountry(@PathVariable String code) {
        this.logger.info("GET /countries/" + code);
        try {
            Country country = this.countryService.getByCode(code);
            if(country == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(country, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) { //lecture12_RestOWn

        this.logger.info("POST /countries/\n" + country);
        try {
            if(this.countryService.getByCode(country.getCode()) != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            this.countryService.create(country);
            return new ResponseEntity<>(country, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{code}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteCountry(@PathVariable String code) {
        this.logger.info("DELETE /countries" + code);
        try {
            Country country = this.countryService.getByCode(code);
            if(country == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            this.countryService.delete(country);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{code}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Country> updateCountry(@PathVariable String code, @RequestBody String httpBody) {
        this.logger.info("PUT /countries/" +  code + "\n" + httpBody);
        try {
            Country country = this.countryService.getByCode(code);
            if(country == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            JSONObject rawData = new JSONObject(httpBody);
            if(rawData.has("name")) {
                country.setName(rawData.getString("name"));
            }
            this.countryService.update(country);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }







}
