package lsandor.tower;

import lsandor.aircraft.flyable.Flyable;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static lsandor.simulator.Simulator.FILE_NAME;

public class Tower {

    private final List<Flyable> observers = new ArrayList<>();
    private final List<Flyable> unregistered = new ArrayList<>();
    private final File file = new File(FILE_NAME);

    public void register(Flyable flyable) {
        if (observers.contains(flyable)) {
            return;
        }
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        if (unregistered.contains(flyable)) {
            return;
        }
        unregistered.add(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
        observers.removeAll(unregistered);
        unregistered.clear();
    }

    public void writeToFile(String textToWrite) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(textToWrite);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
    }

}
