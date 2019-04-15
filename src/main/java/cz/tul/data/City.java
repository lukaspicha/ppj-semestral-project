package cz.tul.data;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "openweathermap_name")
    protected String openWeatherMapName;

    @ManyToOne(fetch = FetchType.EAGER) // https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
    @JoinColumn(name = "country_code")
    protected Country country;

    public City() {

    }

    public City(Country country, String name, String openWeatherMapName) {
        this.country = country;
        this.name = name;
        this.openWeatherMapName = openWeatherMapName;
    }

    public City(int id, Country country, String name, String openWeatherMapName) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.openWeatherMapName = openWeatherMapName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    // ve skutecnosti se jedna o id, spatne pojmenovani
    public String getOpenWeatherMapName() {
        return this.openWeatherMapName;
    }

    public void  setOpenWeatherMapName(String openWeatherMapName) {
         this.openWeatherMapName = openWeatherMapName;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", openEatherMapName='" + this.openWeatherMapName + '\'' +
                ", country=" +this.country +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (openWeatherMapName != null ? !openWeatherMapName.equals(city.openWeatherMapName) : city.openWeatherMapName != null)
            return false;
        return country != null ? country.equals(city.country) : city.country == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (openWeatherMapName != null ? openWeatherMapName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
