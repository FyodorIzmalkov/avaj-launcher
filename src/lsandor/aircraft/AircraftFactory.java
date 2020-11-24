package lsandor.aircraft;

import lsandor.aircraft.flyable.Flyable;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "Helicopter":
                return new Helicopter(name, coordinates);
            case "Baloon":
                return new Baloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            default:
                System.out.println("Error: invalid aircraft type was asked from AircraftFactory.");
                System.exit(1);
                return null;
        }
    }

}
