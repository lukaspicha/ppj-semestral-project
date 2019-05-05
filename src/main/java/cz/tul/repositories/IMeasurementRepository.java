package cz.tul.repositories;
import cz.tul.data.Measurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface IMeasurementRepository extends MongoRepository<Measurement, ObjectId> {


    @Query("{measuredTimestamp : { $lt: ?0}}")
    void deleteByMeasuredTimestamp(long measuredTimestamp);

    @Query("{ openWeatherMapName: ?0, measuredTimestamp: {$gte: ?1} }")
    List<Measurement> findByOpenWeatherMapNameAndTimeGreaterThen(String openWeatherMapName, long time);

    @Query("{ openWeatherMapName: {$in: ?0}}, $orderby: {measuredTimestamp : 1}")
    List<Measurement> findMeasurementsForOpenWeatherMapName(List<String> openWeatherMapNames);

    List<Measurement> findByOpenWeatherMapName(String openWeatherMapName);

    //prumery pro dane mesto od urciteho datumu
    // db.measurements.aggregate([
    //                     { $match: { openWeatherMapName: "524901" , measuredTimestamp: {$gte: 1556650211 }} },
    //                     { $group: { _id: "$openWeatherMapName", pressureAvg: { $avg: "$pressure" },  humidityAvg: { $avg: "$humidity" },  tempAvg: { $avg: "$temp" } } }
    //                   ])

    // Aggregation aggregation = newAggregation(
    //                match(Criteria.where("openWeatherMapName").is(openWeatherMapName)),
    //                match(Criteria.where("measuredTimestamp").gte(time)),
    //                group("openWeatherMapName").avg("temp").as("tempAvg").avg("pressure").as("pressureAvg").avg("humidity").as("humidityAvg"));

    //prumery pro vsechna mesta od urciteho datumu
    // db.measurements.aggregate([
    //                     { $match: {  measuredTimestamp: {$gte: 1556650211 }} },
    //                     { $group: { _id: "$openWeatherMapName", pressureAvg: { $avg: "$pressure" },  humidityAvg: { $avg: "$humidity" },  tempAvg: { $avg: "$temp" } } }
    //                   ])
    

}
