package cz.tul.data.services;

import cz.tul.Main;
import cz.tul.data.Measurement;
import cz.tul.service.MeasurementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"devel"})
public class MeasurementServiceTests {

    @Autowired
    protected MeasurementService measurementService;


    @Test
    public void getAveragesByOpenWeatherMapNameAndTimeGreaterThanTest() {
        this.measurementService.insert(new Measurement("524901", (float)10.2, 1002, 32, 1556731860, "metrics"));
        this.measurementService.insert(new Measurement("524902", (float) 8.2, 1009, 33, 1556731865, "metrics"));
        this.measurementService.insert(new Measurement("624901", (float)7.7, 1010, 33, 1556731872, "metrics"));
        this.measurementService.insert(new Measurement("524901", (float)10.2, 1001, 33, 1556731887, "metrics"));


        assertEquals(this.measurementService.getAll().size(), 4);
    }

}
