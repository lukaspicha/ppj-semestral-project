package cz.tul.data.controllers.web;

import cz.tul.data.Country;
import cz.tul.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {

    protected  CountryService countryService;

    @Autowired
    public void setCountryService(CountryService countryService) { //lecture11_springMvc
        this.countryService = countryService;
    }

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
            return "home/detail";
        }

        return "home/index";

    }


}
