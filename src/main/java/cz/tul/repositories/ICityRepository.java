package cz.tul.repositories;

import cz.tul.data.City;
import org.springframework.data.repository.CrudRepository;

public interface ICityRepository extends CrudRepository<City, Integer> {

}
