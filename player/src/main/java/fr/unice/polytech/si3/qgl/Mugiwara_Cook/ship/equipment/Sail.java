package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

/**
 * Voile
 */
public class Sail extends Equipment {
    public final static String TYPE = "sail";
    int x;
    int y;
    boolean openned;

    public Sail(int x, int y, boolean openned) {
        super(TYPE,x,y);
        this.openned = openned;
        this.x=x;
        this.y=y;
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
