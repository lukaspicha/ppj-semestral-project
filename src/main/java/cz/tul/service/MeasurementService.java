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

    public void deleteOldData(long time) {
        this.measurementRepository.deleteByMeasuredTimestamp(time);
    }


    public AggregationResults<WeatherData> getAveragesByOpenWeatherMapName(String openWeatherMapName, long time) {

        GroupOperation avgTemp = group("openWeatherMapName").avg("temp").as("tempAvg");
        GroupOperation avgPressure = group("openWeatherMapName").avg("pressure").as("pressureAvg");
        GroupOperation avgHumidity = group("openWeatherMapName").avg("humidity").as("humidityAvg");

        MatchOperation filterByParams = match(new Criteria("openWeatherMapName").is(openWeatherMapName).andOperator(new Criteria("measuredTimestamp").gte(time)));

        Aggregation agg = newAggregation(avgTemp, avgPressure, avgHumidity, filterByParams).withOptions(newAggregationOptions().cursor(new BasicDBObject()).build());
        AggregationResults<WeatherData> result = this.mongoTemplate.aggregate(agg, "measurements", WeatherData.class);
        return result;
    }

}
