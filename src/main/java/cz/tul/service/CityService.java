package cz.tul.service;

import cz.tul.data.City;
import cz.tul.repositories.ICityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    protected ICityRepository cityRepository;

    public void create(City city) {
        this.cityRepository.save(city);
    }

    public List<City> selectAll() {
        return StreamSupport.stream(this.cityRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void delete() {
        this.cityRepository.deleteAll();
    }

    public void delete(City country) {
        this.cityRepository.delete(country);
    }

    public City getById(int id) {
        return this.cityRepository.findOne(id);
    }

    public List<City> findByName(String name) {
        return this.cityRepository.findByName(name);
    }

    public List<City> findByCountryCode(String country_code) {
        return this.cityRepository.findByCountryCode(country_code);
    }

    public void update(City city) {
        this.cityRepository.save(city);
    }

    public List<String> getAllOpenWeatherMapNames() {
        List<String> names = new ArrayList<String>();
        for (City city : this.selectAll()) {
           if(city.getOpenWeatherMapName() != null) {
               names.add(city.getOpenWeatherMapName());
           }
        }
        return names;
    }


}
