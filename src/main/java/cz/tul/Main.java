package cz.tul;

import cz.tul.data.Country;
import cz.tul.data.City;
import cz.tul.service.CityService;
import cz.tul.service.CountryService;
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
    public CityService cityResource() {
        return new CityService();
    }

    @Bean
    public CountryService countryService() {
        return new CountryService();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        CountryService countryService = ctx.getBean(CountryService.class);
        List<Country> countries = countryService.selectAll();
        System.out.println(countries);

        CityService cityService = ctx.getBean(CityService.class);
        List<City> cities = cityService.selectAll();
        System.out.println(cities);


    }

}