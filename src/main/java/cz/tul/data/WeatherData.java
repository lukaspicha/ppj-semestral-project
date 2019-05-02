package cz.tul.data;

import javax.persistence.Id;

public class WeatherData {

    @Id
    protected String openWeatherMapName;

    protected float tempAvg;
    protected float humidityAvg;
    protected float pressureAvg;

    public WeatherData() {

    }

    public WeatherData(String openWeatherMapName, float tempAvg, float humidityAvg, float pressureAvg) {
        this.openWeatherMapName = openWeatherMapName;
        this.tempAvg = tempAvg;
        this.humidityAvg = humidityAvg;
        this.pressureAvg = pressureAvg;
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

    @Override
    public String toString() {
        return "WeatherData{" +
                "openWeatherMapName='" + openWeatherMapName + '\'' +
                ", tempAvg=" + tempAvg +
                ", humidityAvg=" + humidityAvg +
                ", pressureAvg=" + pressureAvg +
                '}';
    }
}
