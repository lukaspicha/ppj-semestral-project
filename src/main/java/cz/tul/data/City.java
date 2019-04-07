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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
    protected Country country;

    public City() {

    }

    public City(Country country, String name) {
        this.country = country;
        this.name = name;
    }

    public City(int id, Country country, String name) {
        this.id = id;
        this.country = country;
        this.name = name;
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

    @Override
    public String toString() {
        return "City[id=" + this.id + ", name=" + this.name +  ", city=" + this.country + "]";
    }

}
