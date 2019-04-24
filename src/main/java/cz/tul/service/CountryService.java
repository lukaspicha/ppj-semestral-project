package cz.tul.service;

import cz.tul.data.Country;
import cz.tul.repositories.ICountryRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryService {

    @Autowired
    protected ICountryRepository countryRepository;


    public void create(Country country) {
        this.countryRepository.save(country);
    }

    public List<Country> selectAll() {
        return StreamSupport.stream(this.countryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void delete() {
        this.countryRepository.deleteAll();
    }

    public void delete(Country country) {
        this.countryRepository.delete(country);
    }

    public Country getByCode(String code) {
        return this.countryRepository.findOne(code);
    }


    public void update(Country country) {
        this.countryRepository.save(country);
    }




}
