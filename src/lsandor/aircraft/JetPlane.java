package lsandor.aircraft;

import lsandor.aircraft.flyable.Flyable;
import lsandor.tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);
        String toFile = "";
        String toFileUnreg = "";
        String resultStr = "JetPlane#" + this.name + "(" + this.id + "): ";

        switch (weatherType) {
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight());
                toFile = resultStr + "Watch out for lightnings!\n";
                break;
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2);
                toFile = resultStr + "This sun is getting to my eyes!\n";
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight());
                toFile = resultStr + "Gamn fog!\n";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7);
                toFile = resultStr + "We are going to freeze!\n";
                break;
        }

        weatherTower.writeToFile(toFile);

        if (this.coordinates.getHeight() <= 0) {
            toFileUnreg = "Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered from weather tower.\n";
            weatherTower.writeToFile(toFileUnreg);
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String stringToWrite = "Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.\n";
        weatherTower.writeToFile(stringToWrite);
    }

}
