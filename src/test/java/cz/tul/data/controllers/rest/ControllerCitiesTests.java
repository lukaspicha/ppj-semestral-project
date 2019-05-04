package cz.tul.data.controllers.rest;

import cz.tul.Main;
import cz.tul.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
public class ControllerCitiesTests {


    @Autowired
    private CountryService countryService;

    @Autowired
    private CountriesController countriesController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.countriesController).build();

    }


    @Test
    public void testGetAll() throws Exception {
        this.mockMvc.perform(get("/countries").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

    }

    @Test
    public void testGetSpecicif() throws  Exception {
        this.mockMvc.perform(get("/countries/CZE").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void testGetUndefined() throws  Exception {
        this.mockMvc.perform(get("/countries/CAN").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
        ;
    }






}
