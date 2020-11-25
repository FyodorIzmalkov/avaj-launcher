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
        String resultStr = "Baloon#" + this.name + "(" + this.id + "): ";

        switch (weatherType) {
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5);
                toFile = resultStr + "IT IS RAINING WATER WATER EVERYWHERE!\n";
                break;
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4);
                toFile = resultStr + "IT IS HOT TOO HOT TO FLY!\n";
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3);
                toFile = resultStr + "IT IS FOG I CANT SEE A METER AHEAD!\n";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15);
                toFile = resultStr + "IT IS SNOWING WHAT SHOULD WE DO NOW?!\n";
                break;
        }

        weatherTower.writeToFile(toFile);

        if (this.coordinates.getHeight() <= 0) {
            weatherTower.writeToFile("Baloon#" + this.name + "(" + this.id + ") landing. " + coordinates.toString() + "\n");
            String unregister = "Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered from weather tower.\n";
            weatherTower.writeToFile(unregister);
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String stringToWrite = "Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.\n";
        weatherTower.writeToFile(stringToWrite);
    }

}
