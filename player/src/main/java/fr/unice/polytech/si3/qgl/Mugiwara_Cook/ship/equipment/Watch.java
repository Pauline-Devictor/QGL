package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

/**
 * Vigie
 */
public class Watch extends Equipment {
    public final static String TYPE = "watch";
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
