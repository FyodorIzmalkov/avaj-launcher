package lsandor.simulator;

import lsandor.aircraft.AircraftFactory;
import lsandor.aircraft.flyable.Flyable;
import lsandor.exception.EmptyFileContentException;
import lsandor.exception.WrongFileContentException;
import lsandor.exception.WrongUsageException;
import lsandor.tower.WeatherTower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private static final int TYPE = 0;
    private static final int NAME = 1;
    private static final int LONGITUDE = 2;
    private static final int LATITUDE = 3;
    private static final int HEIGHT = 4;

    public static void main(String[] args) {

        try {
            if (args.length != 1) {
                throw new WrongUsageException("Correct usage is: java lsandor.simulator.Simulator [filename]");
            }

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String currentString = reader.readLine();
            if (currentString == null) {
                throw new EmptyFileContentException("Error: file content is empty.");
            }

            int numberOfSimulations = Integer.parseInt(currentString.split(" ")[0]);
            if (numberOfSimulations <= 0) {
                System.out.println("Error: number of simulations can not be <= 0");
                System.exit(1);
            }

            WeatherTower weatherTower = new WeatherTower();
            List<Flyable> aircraftList = new ArrayList<>();
            while ((currentString = reader.readLine()) != null) {
                String[] data = currentString.split(" ");

                if (data.length == 5) {
                    Flyable flyable = AircraftFactory.newAircraft(
                            data[TYPE],
                            data[NAME],
                            Integer.parseInt(data[LONGITUDE]),
                            Integer.parseInt(data[LATITUDE]),
                            Integer.parseInt(data[HEIGHT]));
                    flyable.registerTower(weatherTower);
                    aircraftList.add(flyable);
                } else {
                    throw new WrongFileContentException("Error: After the first line, every line should have information in the following structure: [TYPE NAME LONGITUDE LATITUDE HEIGHT]");
                }
            }
            reader.close();

            for (int i = 0; i < numberOfSimulations; i++) {
                weatherTower.changeWeatherForAllAircraft();
            }

        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

}
