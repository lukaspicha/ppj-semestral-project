package cz.tul.data.controllers.rest;

import cz.tul.data.Measurement;
import cz.tul.service.CityService;
import cz.tul.service.MeasurementService;
import cz.tul.service.OpenWeatherMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class FeedController {

    protected Logger logger = LoggerFactory.getLogger(FeedController.class);

    protected CityService cityService;

    protected MeasurementService measurementService;

    protected OpenWeatherMapService openWeatherMapService;

    @Value("{openWeatherMap.expireAfterSeconds}")
    protected int expireAfterSeconds = 1209600;

    @Autowired
    public void setCityService(CityService cityService) { //lecture11_springMvc
        this.cityService = cityService;
    }

    @Autowired
    public void setOpenWeatherMapService(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }

    @Autowired
    public void setMeasurementService(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }


    @RequestMapping(value = "/feed/download", method = RequestMethod.GET, produces = "application/json")
    @Scheduled(cron = "${openWeatherMap.cronDownload}")
    public void download() {
        this.logger.info("GET /feed/download");
        try {
            List<String> cities = this.cityService.getAllOpenWeatherMapNames();
            if(cities.size() > 0) {
               List<Measurement> measurements = this.openWeatherMapService.downloadWeatherDataByCitiesIds(cities);
               if(measurements.size() > 0) {
                   this.logger.info("Adding a " + measurements.size() + " measuremnets to db");
                   this.measurementService.insertMultiple(measurements);
               } else {
                   this.logger.info("After downloadind from OWM is list of measurements empty");
               }

            } else {
                this.logger.info("Cannot download from OWM, because list of getAllOpenWeatherMapNames is empty");
            }
        } catch(Exception e) {
            this.logger.error(e.getMessage(), e);
        }
    }


    @RequestMapping(value = "/feed/delete-old", method = RequestMethod.GET, produces = "application/json")
    @Scheduled(cron = "${openWeatherMap.cronDelete}")
    public void deleteOldData() {
        Date date = new Date();
        long time = date.getTime();
        this.logger.info("GET /feed/delete-old/" + date.toString());
        this.measurementService.deleteOldData(time - this.expireAfterSeconds);
    }








}
