package cz.tul.repositories;

import cz.tul.data.City;
import cz.tul.data.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICityRepository extends CrudRepository<City, Integer> {

    List<City> findByName(String name);

    List<City> findByCountryCode(String country_code);
}
