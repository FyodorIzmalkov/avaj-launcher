package lsandor.weather.provider;

import lsandor.aircraft.Coordinates;

import java.util.concurrent.ThreadLocalRandom;

public class WeatherProvider {

    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int sum = 0;
        sum += coordinates.getHeight();
        sum += coordinates.getLatitude();
        sum += coordinates.getLongitude();
        return weather[ThreadLocalRandom.current().nextInt(sum) % weather.length];
    }

}
