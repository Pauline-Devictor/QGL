package fr.unice.polytech.si3.qgl.mugiwara_cook.ship.equipment;

/**
 * Vigie
 */
public class Watch extends Equipment {
    public final static String TYPE = "Watch";
    String type = TYPE;
    int x;
    int y;

    public Watch(int x, int y) {
        super(TYPE);
        this.x = x;
        this.y = y;
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
}
