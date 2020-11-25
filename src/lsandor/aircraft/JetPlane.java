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
        String resultStr = "JetPlane#" + this.name + "(" + this.id + "): ";

        switch (weatherType) {
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight());
                toFile = resultStr + "It is too rainy!\n";
                break;
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2);
                toFile = resultStr + "It is too sunny!\n";
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight());
                toFile = resultStr + "It is too foggy!\n";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7);
                toFile = resultStr + "It is too snowy!\n";
                break;
        }

        weatherTower.writeToFile(toFile);

        if (this.coordinates.getHeight() <= 0) {
            weatherTower.writeToFile("JetPlane#" + this.name + "(" + this.id + ") landing. " + coordinates.toString() + "\n");
            String unregister = "Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered from weather tower.\n";
            weatherTower.writeToFile(unregister);
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
