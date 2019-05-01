package cz.tul.repositories;
import cz.tul.data.Measurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface IMeasurementRepository extends MongoRepository<Measurement, ObjectId> {


    @Query("{measuredTimestamp : { $lt: ?0}}")
    void deleteByMeasuredTimestamp(long measuredTimestamp);

    List<Measurement> findByOpenWeatherMapName(String openWeatherMapName);

}
