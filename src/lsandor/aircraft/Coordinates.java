package lsandor.aircraft;

public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = Math.max(longitude, 0);
        this.latitude = Math.max(latitude, 0);
        this.height = height > 100 ? 100 : Math.max(height, 0);
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

}
