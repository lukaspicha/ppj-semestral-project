package cz.tul.service;

import cz.tul.data.Measurement;
import cz.tul.repositories.IMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {


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

    public void deleteOlddata(long time) {
        this.measurementRepository.deleteByMeasuredTimestamp(time);
    }

}
