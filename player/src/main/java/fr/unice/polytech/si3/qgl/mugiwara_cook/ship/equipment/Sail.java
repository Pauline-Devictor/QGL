package fr.unice.polytech.si3.qgl.mugiwara_cook.ship.equipment;

/**
 * Voile
 */
public class Sail extends Equipment {
    public final static String TYPE = "Sail";
    int x;
    int y;
    boolean openned;

    public Sail(int x, int y, boolean openned) {
        super(TYPE);
        this.x = x;
        this.y = y;
        this.openned = openned;
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

    public boolean isOpenned() {
        return openned;
    }

    public void setOpenned(boolean openned) {
        this.openned = openned;
    }


}
