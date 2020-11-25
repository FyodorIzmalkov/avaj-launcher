package lsandor.tower;

import lsandor.aircraft.flyable.Flyable;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Tower {

    private final List<Flyable> observers = new ArrayList<>();
    private final List<Flyable> unreg = new ArrayList<>();
    private FileWriter writer;
    private File file;

    // private static final String SCENARIO = "simulation.txt";

    public void register(Flyable flyable) {
        if (observers.contains(flyable)) {
            return;
        }
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        if (unreg.contains(flyable)) {
            return;
        }
        unreg.add(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
        observers.removeAll(unreg);
    }

    public void writeToFile(String textToWrite) {
        try {
            this.file = new File("simulation.txt");
            this.writer = new FileWriter(file, true);
            this.file.createNewFile();

            writer.write(textToWrite);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
    }

}
