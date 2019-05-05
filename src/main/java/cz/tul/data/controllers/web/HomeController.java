package cz.tul.data.controllers.web;

import cz.tul.data.City;
import cz.tul.data.Country;
import cz.tul.data.WeatherData;
import cz.tul.service.CityService;
import cz.tul.service.CountryService;
import cz.tul.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.*;


@Controller
public class HomeController {

    protected  CountryService countryService;
    protected CityService cityService;
    protected MeasurementService measurementService;

    @Autowired
    public void setCountryService(CountryService countryService) { //lecture11_springMvc
        this.countryService = countryService;
    }

    @Autowired
    public void setCityService(CityService cityService) {this.cityService = cityService;}

    @Autowired
    public void setMeasurementService(MeasurementService measurementService) {this.measurementService = measurementService;}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(ModelMap model) {
        List<Country> countries = this.countryService.selectAll();
        model.addAttribute("countries", countries);

        return "home/index";
    }

    @RequestMapping(value = "/home/detail/{countryCode}", method = RequestMethod.GET)
    public String welcomeName(@PathVariable String countryCode, ModelMap model) {
        Country country = this.countryService.getByCode(countryCode);
        if(country != null) {
            model.addAttribute("country", country);
            List<City> cities = this.cityService.findByCountryCode(country.getCode());
            Map<City, List<WeatherData>> weatherData = new HashMap<City, List<WeatherData>>();
            long time = System.currentTimeMillis() / 1000;

            for (City c : cities) {

                List<WeatherData> weatherAverages = new ArrayList<WeatherData>();
                weatherAverages.add(this.measurementService.getAveragesByOpenWeatherMapNameAndTimeGreaterThan(c.getOpenWeatherMapName(), time - 3600));
                weatherAverages.add(this.measurementService.getAveragesByOpenWeatherMapNameAndTimeGreaterThan(c.getOpenWeatherMapName(), time - 1209600));
                weatherAverages.add(this.measurementService.getAveragesByOpenWeatherMapNameAndTimeGreaterThan(c.getOpenWeatherMapName(), time - (2 * 1209600)));

                weatherData.put(c, weatherAverages);
            }
            model.addAttribute("weatherData", weatherData);
            System.out.println(weatherData);
            return "home/detail";
        }

        return "home/index";

    }


}
