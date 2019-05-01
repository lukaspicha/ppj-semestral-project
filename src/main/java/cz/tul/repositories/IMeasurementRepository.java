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

    //prumery pro dane mesto od urciteho datumu
    // db.measurements.aggregate([
    //                     { $match: { openWeatherMapName: "524901" , measuredTimestamp: {$gte: 1556650211 }} },
    //                     { $group: { _id: "$openWeatherMapName", pressureAvg: { $avg: "$pressure" },  humidityAvg: { $avg: "$humidity" },  tempAvg: { $avg: "$temp" } } }
    //                   ])

    //prumery pro vsechna mesta od urciteho datumu
    // db.measurements.aggregate([
    //                     { $match: {  measuredTimestamp: {$gte: 1556650211 }} },
    //                     { $group: { _id: "$openWeatherMapName", pressureAvg: { $avg: "$pressure" },  humidityAvg: { $avg: "$humidity" },  tempAvg: { $avg: "$temp" } } }
    //                   ])


}
