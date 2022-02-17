package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

/**
 * Voile
 */
public class Sail extends Equipment {
    public final static String TYPE = "sail";
    boolean openned;

    public Sail(int x, int y, boolean openned) {
        super(TYPE,x,y);
        this.openned = openned;
    }

    public boolean isOpenned() {
        return openned;
    }

    public void setOpenned(boolean openned) {
        this.openned = openned;
    }


}
