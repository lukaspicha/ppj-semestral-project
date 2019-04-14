package cz.tul;

import com.fasterxml.jackson.databind.JsonNode;
import cz.tul.data.Country;
import cz.tul.data.City;
import cz.tul.data.Measurement;
import cz.tul.service.CityService;
import cz.tul.service.CountryService;
import cz.tul.service.MeasurementService;
import cz.tul.service.OpenWeatherMapService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.List;


@SpringBootApplication
@EnableJpaRepositories("cz.tul.repositories")
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public CityService cityService() {
        return new CityService();
    }

    @Bean
    public CountryService countryService() {
        return new CountryService();
    }

    @Bean
    public OpenWeatherMapService openWeatherMapService() {return new OpenWeatherMapService();}

   @Bean
   public MeasurementService measurementService() {return new MeasurementService();}

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        OpenWeatherMapService openWeatherMapService = ctx.getBean(OpenWeatherMapService.class);

        MeasurementService measurementService = ctx.getBean(MeasurementService.class);
        Measurement m = openWeatherMapService.getByCityId("3339541");
        if(m instanceof Measurement) {
            System.out.println(m);
            //measurementService.insert(m);
        }

        List<Measurement> meas = measurementService.getAll();
        System.out.println(meas);

        // CountryService countryService = ctx.getBean(CountryService.class);
        // List<Country> countries = countryService.selectAll();
        // System.out.println(countries);

        // CityService cityService = ctx.getBean(CityService.class);
        // List<City> cities = cityService.selectAll();
        // System.out.println(cities);


    }

}