package cz.tul.data.controllers.rest;

import cz.tul.data.Measurement;
import cz.tul.data.WeatherData;
import cz.tul.service.MeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Document;
import java.util.Date;
import java.util.List;

@RestController
public class WeatherController {

    protected MeasurementService measurementService;

    protected Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    public void setMeasurementService(MeasurementService measurementService) { //lecture11_springMvc
        this.measurementService = measurementService;
    }


    @RequestMapping(value = "/weather/history", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Measurement>> getMeasurements() {
        this.logger.info("GET /weather/history");
        try {
            List<Measurement> measurements = this.measurementService.getAll();
            this.logger.info("Returning a " + measurements.size() + "measuremenets");
            return new ResponseEntity<List<Measurement>>(measurements, HttpStatus.OK);

        } catch(Exception e) {
            this.logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/weather/history/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Measurement>> getCityHistory(@PathVariable String id) {
        this.logger.info("GET /weather/history/" + id);
        try {
            List<Measurement> measurements = this.measurementService.getByOpenWeatherMapName(id);
            this.logger.info("Returning a " + measurements.size() + "measuremenets for id " + id);
            return new ResponseEntity<List<Measurement>>(measurements, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/weather/averages/{id}/{averageFor}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<WeatherData> getWeatherAverages (@PathVariable String id, @PathVariable String averageFor) {
        this.logger.info("GET /weather/averages/" + id + "/" + averageFor);
        try {
            Date date = new Date();
            long time = date.getTime() - this.getIntervalFromParam(averageFor);

            WeatherData weatherData  =  this.measurementService.getAveragesByOpenWeatherMapNameAndTimeGreaterThan(id, 1556731740);

            return new ResponseEntity<WeatherData>(weatherData, HttpStatus.OK);

        } catch(Exception e) {
            this.logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private int getIntervalFromParam(String averageFor) {
        int interval;
        switch(averageFor) {
            case "last-day":
                interval = 24 * 60 * 60;
                break;
            case "last-week":
                interval = 24 * 60 * 60 * 7;
                break;
            case "last-half-moon":
                interval = 24 * 60 * 60 * 14;
                break;
            default:
                interval = 86400;
                break;
        }

        return interval;
    }



}
