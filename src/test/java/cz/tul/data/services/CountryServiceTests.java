package cz.tul.data.services;

import cz.tul.Main;
import cz.tul.data.Country;
import cz.tul.service.CountryService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"devel"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryServiceTests {

    @Autowired
    protected CountryService countryService;


    @Test
    public void testInsertAndGet() {
        Country country_pol = new Country("POL", "Polsko");
        this.countryService.create(country_pol);

        List<Country> countries = this.countryService.selectAll();

        assertEquals("Count of countries after cretaing should be 1", 1, countries.size());
        assertEquals("Countries should be equals", country_pol, this.countryService.getByCode("POL"));


    }
    @Test
    public void testUpdate() {
        Country country_pol = new Country("POL", "Posko");
        this.countryService.create(country_pol);

        String correctStateName = "Polsko";
        country_pol.setName(correctStateName);
        this.countryService.update(country_pol);

        assertEquals("Country name should be " + correctStateName, correctStateName, this.countryService.getByCode("POL").getName());

    }

    @Test
    public void testDeleteAll() {
        this.countryService.delete();
        List<Country> countires = this.countryService.selectAll();

        assertEquals("Count of countries after delete should be 0", 0, countires.size());

    }
}
