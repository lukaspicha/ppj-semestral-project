package cz.tul;

import cz.tul.data.CountriesDao;
import cz.tul.data.Country;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import java.util.List;


@SpringBootApplication
public class Main {

    @Bean
    public CountriesDao countriesDao() {
        return new CountriesDao();
    }



    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);





        CountriesDao countries = ctx.getBean(CountriesDao.class);



        List<Country> countriesList = countries.getAllCountries();
        for(Country c : countriesList) {
            System.out.println(c);
        }

        Country newCountry = new Country("MAU", "Mauthasen");
        System.out.println(newCountry);
        countries.create(newCountry);


    }

}