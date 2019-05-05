package cz.tul.data;

import javax.persistence.Id;
import java.util.Date;

public class WeatherData {

    @Id
    protected String openWeatherMapName;

    protected float tempAvg;
    protected float humidityAvg;
    protected float pressureAvg;

    protected long measuredFromTimestamp;
    protected Date measuredFrom;

    public WeatherData() {

    }

    public WeatherData(String openWeatherMapName, float tempAvg, float humidityAvg, float pressureAvg, long measuredFromTimestamp) {
        this.openWeatherMapName = openWeatherMapName;
        this.tempAvg = tempAvg;
        this.humidityAvg = humidityAvg;
        this.pressureAvg = pressureAvg;
        this.measuredFromTimestamp = measuredFromTimestamp;

        this.measuredFrom = new Date(measuredFromTimestamp * 1000);
    }

    public void setOpenWeatherMapName(String openWeatherMapName) {
        this.openWeatherMapName = openWeatherMapName;
    }

    public void setTempAvg(float tempAvg) {
        this.tempAvg = tempAvg;
    }

    public void setHumidityAvg(float humidityAvg) {
        this.humidityAvg = humidityAvg;
    }

    public void setPressureAvg(float pressureAvg) {
        this.pressureAvg = pressureAvg;
    }

    public void setMeasuredFromTimestamp(long measuredFromTimestamp) {this.measuredFromTimestamp = measuredFromTimestamp;}

    public String getOpenWeatherMapName() {
        return openWeatherMapName;
    }

    public float getTempAvg() {
        return tempAvg;
    }

    public float getHumidityAvg() {
        return humidityAvg;
    }

    public float getPressureAvg() {
        return pressureAvg;
    }

    public long getMeasuredFromimestamp() {
        return this.measuredFromTimestamp;
    }

    public Date getMeasuredFrom() {
        return this.measuredFrom;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "openWeatherMapName='" + openWeatherMapName + '\'' +
                ", tempAvg=" + tempAvg +
                ", humidityAvg=" + humidityAvg +
                ", pressureAvg=" + pressureAvg +
                ", measuredFromTimestamp=" + measuredFromTimestamp +
                '}';
    }
}
