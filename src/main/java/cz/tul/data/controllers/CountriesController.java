package cz.tul.data.controllers;

import cz.tul.data.Country;
import cz.tul.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class CountriesController {

    protected CountryService countryService;

    @Autowired
    public void setCountryService(CountryService countryService) { //lecture11_springMvc
        this.countryService = countryService;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = this.countryService.selectAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }


}
