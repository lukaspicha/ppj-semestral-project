package cz.tul.repositories;

import cz.tul.data.Country;
import org.springframework.data.repository.CrudRepository;

public interface ICountryRepository extends CrudRepository<Country, String> {
}
