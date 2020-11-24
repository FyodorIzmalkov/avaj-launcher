package lsandor.tower;

import lsandor.aircraft.Coordinates;
import lsandor.weather.provider.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        this.conditionsChanged();
    }

    public void changeWeatherForAllAircraft() {
        this.changeWeather();
    }
}