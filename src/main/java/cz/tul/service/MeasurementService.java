package cz.tul.service;

import cz.tul.data.Measurement;
import cz.tul.repositories.IMeasurementRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    public void deleteAll() {
        this.measurementRepository.deleteAll();

    }


    public List<Measurement> getAll() {
        return  this.measurementRepository.findAll();
    }

}
