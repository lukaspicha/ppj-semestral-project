package cz.tul.service;

import com.mongodb.BasicDBObject;
import cz.tul.data.Measurement;
import cz.tul.data.WeatherData;
import cz.tul.repositories.IMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.swing.text.Document;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class MeasurementService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected IMeasurementRepository measurementRepository;
    public MeasurementService() {

    }

    public void insert(Measurement measurement) {
        this.measurementRepository.insert(measurement);
    }


    public void insertMultiple(List<Measurement> measurements) {
        this.measurementRepository.insert(measurements);
    }

    public List<Measurement> getAll() {
        return  this.measurementRepository.findAll();
    }

    public List<Measurement> getByOpenWeatherMapName(String openWeatherMapName) {
        return this.measurementRepository.findByOpenWeatherMapName(openWeatherMapName);
    }

    public List<Measurement> findMeasurementsForOpenWeatherMapName(List<String> openWeatherMapNames) {
        return this.measurementRepository.findMeasurementsForOpenWeatherMapName(openWeatherMapNames);
    }

    public void deleteOldData(long time) {
        this.measurementRepository.deleteByMeasuredTimestamp(time);
    }

    //db.measurements.aggregate(
    // {$match: {
    //      "openWeatherMapName": {$in: ["3071961", "524901"]}}
    // },
    // {$group: {
    //          _id: "$measured_timestamp",
    //          records: {$push: "$$ROOT"}
    //      }
    // }
    // )

    // db.measurements.aggregate({$match: {"openWeatherMapName": {$in: ["3071961", "524901"]}}},{$group: {_id: "$measured_timestamp", records: {$push: "$$ROOT"}}})

    public void getAveragesByOpenWeatherMapName() {

        //TypedAggregation<WeatherData> aff = newAggregation(W)
    }
    public WeatherData getAveragesByOpenWeatherMapNameAndTimeGreaterThan(String openWeatherMapName, long time) {

        List<Measurement> meas = this.measurementRepository.findByOpenWeatherMapNameAndTimeGreaterThen(openWeatherMapName, time);
        float sumOfTemp = 0;
        float sumOfHumidity = 0;
        float sumOfPress = 0;
        int n = 0;
        for (Measurement m : meas) {
            sumOfTemp += m.getTemp();
            sumOfHumidity += m.getHumidity();
            sumOfPress += m.getPressure();
            n++;
        }

        WeatherData weatherData = new WeatherData(openWeatherMapName, sumOfTemp > 0 ? sumOfTemp / n : 0,  sumOfHumidity > 0 ? sumOfHumidity / n : 0 , sumOfPress > 0 ? sumOfPress / n : 0, time);
        return weatherData;

    }

}
