package cz.tul.repositories;
import cz.tul.data.Measurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface IMeasurementRepository extends MongoRepository<Measurement, ObjectId> {


}
