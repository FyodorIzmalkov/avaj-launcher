package lsandor.aircraft;

import lsandor.aircraft.flyable.Flyable;
import lsandor.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;


    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);
        String toFile = "";
        String resultStr = "Helicopter#" + this.name + "(" + this.id + "): ";

        switch (weatherType) {
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                toFile = resultStr + "Ohhhh I hate rain!\n";
                break;
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2);
                toFile = resultStr + "Ohhhh I like sun!\n";
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                toFile = resultStr + "Ohhhh I think fog is just fine!\n";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12);
                toFile = resultStr + "Ohhhh I think snow is what we need now!\n";
                break;
        }

        weatherTower.writeToFile(toFile);

        if (this.coordinates.getHeight() <= 0) {
            weatherTower.writeToFile("Helicopter#" + this.name + "(" + this.id + ") landing. " + coordinates.toString() + "\n");
            String unregister = "Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered from weather tower.\n";
            weatherTower.writeToFile(unregister);
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String stringToWrite = "Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.\n";
        weatherTower.writeToFile(stringToWrite);
    }

}
