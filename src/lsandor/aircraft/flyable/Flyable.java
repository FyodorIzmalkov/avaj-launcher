package lsandor.aircraft.flyable;

import lsandor.tower.WeatherTower;

public interface Flyable {

    void updateConditions();

    void registerTower(WeatherTower weatherTower);

}