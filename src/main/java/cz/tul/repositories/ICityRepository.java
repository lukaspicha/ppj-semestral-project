package cz.tul.repositories;

import cz.tul.data.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityRepository extends CrudRepository<City, Integer> {

    List<City> findByName(String name);

    List<City> findByCountryCode(String country_code);

}
