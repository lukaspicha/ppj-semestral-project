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
    protected String openEatherMapName;

    @ManyToOne(fetch = FetchType.EAGER) // https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
    @JoinColumn(name = "country_code")
    protected Country country;

    public City() {

    }

    public City(Country country, String name, String openEatherMapName) {
        this.country = country;
        this.name = name;
        this.openEatherMapName = openEatherMapName;
    }

    public City(int id, Country country, String name, String openEatherMapName) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.openEatherMapName = openEatherMapName
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

    public String getOpenEatherMapName() {
        return this.openEatherMapName;
    }

    public void  setOpenEatherMapName(String openEatherMapName) {
         this.openEatherMapName = openEatherMapName;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openEatherMapName='" + openEatherMapName + '\'' +
                ", country=" + country +
                '}';
    }
}
