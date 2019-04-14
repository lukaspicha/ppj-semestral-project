package cz.tul.data;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CitiesDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;



    public List<City> getCitties() {

        return jdbc
                .query("select cities.id as id,  cities.name as city_name, countries.code as country_code, countries.name as country_name, openweathermap_name from cities left join countries on (cities.code = countries.code)",
                        (ResultSet rs, int rowNum) -> {
                            return new City(rs.getInt("id"),  new Country(rs.getString("country_code"), rs.getString("country_name")), rs.getString("city_name"), rs.getString("openweathermap_name"));
                        }
                );
    }



    public boolean create(City city) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", city.getName());
        params.addValue("country_code", city.getCountry().getCode());

        return jdbc.update("insert into countries (name, country_code) values (:name, :country_code)", params) == 1;
    }




    public void deleteCities() {
        jdbc.getJdbcOperations().execute("DELETE FROM cities");
    }


}
