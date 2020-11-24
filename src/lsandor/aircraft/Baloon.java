package lsandor.aircraft;

import lsandor.aircraft.flyable.Flyable;
import lsandor.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);
        String toFile = "";
        String toFileUnreg = "";
        String resultStr = "Baloon#" + this.name + "(" + this.id + "): ";

        switch (weatherType) {
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5);
                toFile = resultStr + "It's rain! It messed up my baloon!\n";
                break;
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4);
                toFile = resultStr + "There is some sun, let's enjoy good weather!\n";
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3);
                toFile = resultStr + "I hate fog, I cannot see anything at all!\n";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15);
                toFile = resultStr + "It's snowing! We should be more careful!\n";
                break;
        }

        weatherTower.writeToFile("write", toFile);

        if (this.coordinates.getHeight() <= 0) {
            toFileUnreg = "Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered from weather tower.\n";
            weatherTower.writeToFile("write", toFileUnreg);
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String stringToWrite = "Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.\n";
        weatherTower.writeToFile("write", stringToWrite);
    }

}
