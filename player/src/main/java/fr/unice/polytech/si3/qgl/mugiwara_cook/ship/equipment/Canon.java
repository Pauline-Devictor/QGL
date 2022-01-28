package fr.unice.polytech.si3.qgl.mugiwara_cook.ship.equipment;

public class Canon extends Equipment {
    public final static String TYPE = "Canon";
    String type = TYPE;
    int x;
    int y;
    boolean loaded;
    double angle;

    public Canon(int x, int y,boolean loaded,double angle) {
        super(TYPE);
        this.x = x;
        this.y = y;
        this.loaded = loaded;
        this.angle = angle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

}
