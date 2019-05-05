package cz.tul.data.controllers.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ControllerWeatherTests {

    private MockMvc mockMvc;

    @Autowired
    private WeatherController weatherController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.weatherController).build();

    }

    @Test
    public void testGetAll() throws Exception {
        this.mockMvc.perform(get("/weather/history").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

    }

}
