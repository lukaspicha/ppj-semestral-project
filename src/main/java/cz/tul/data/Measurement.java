package cz.tul.data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "measurements")
public class Measurement {

    @Id
    protected ObjectId id;

    protected String openWeatherMapName;

    protected float temp; //dle units, snazim se vzdy tahat v stupnich Celsia
    protected int pressure; // v hPA
    protected int humidity; //vlhkost vzduchu v %

    protected long measured_timestamp;



    protected Date mesaured_at;

    protected String unit;


    public Measurement() {

    }


    public Measurement(ObjectId id, String openWeatherMapName, float temp, int pressure, int humidity, long measured_timestamp, String unit) {
        this.id = id;
        this.openWeatherMapName = openWeatherMapName;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.measured_timestamp = measured_timestamp;
        this.unit = unit;

        this.mesaured_at = new Date(measured_timestamp * 1000);

    }

    public Measurement(String openWeatherMapName, float temp, int pressure, int humidity, long measured_timestamp, String unit) {
        this.openWeatherMapName = openWeatherMapName;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.measured_timestamp = measured_timestamp;
        this.unit = unit;

        this.mesaured_at = new Date(measured_timestamp * 1000);

    }

    public ObjectId getId() {
        return this.id;
    }

    public String getOpenWeatherMapName() { return this.openWeatherMapName; }

    public float getTemp() {
        return this.temp;
    }

    public float getPressure() {
        return this.pressure;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public long getMeasured_timestamp() {
        return this.measured_timestamp;
    }

    public Date getMesaured_at() {
        return this.mesaured_at;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setOpenWeatherMapName(String openWeatherMapName) {this.openWeatherMapName = openWeatherMapName; }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setMeasured_timestamp(long measured_timestamp) {
        this.measured_timestamp = measured_timestamp;
    }

    public void setMesaured_at(Date mesaured_at) {
        this.mesaured_at = mesaured_at;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", openWeatherMapName=" + openWeatherMapName +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", measured_timestamp=" + measured_timestamp +
                ", mesaured_at=" + mesaured_at +
                ", unit='" + unit + '\'' +
                '}';
    }







}
