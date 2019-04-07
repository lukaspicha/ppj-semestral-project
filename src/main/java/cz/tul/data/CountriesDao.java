package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;
import cz.tul.data.Country;
import java.util.List;

public class CountriesDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Country country) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("code", country.getCode());
        params.addValue("name", country.getName());

        return jdbc.update("insert into countries (code, name) values (:code, :name)", params) == 1;
    }



    public List<Country> getAllCountries() {
        return jdbc.query("select countries.code, countries.name from countries", BeanPropertyRowMapper.newInstance(Country.class));

    }

    public void deleteCountries() {
        jdbc.getJdbcOperations().execute("DELETE FROM countries");
        jdbc.getJdbcOperations().execute("DELETE FROM cities");
    }
}


