package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

/**
 * Rame
 */
public class Oar extends Equipment {
    public final static String TYPE = "oar";
    int x;
    int y;


    public Oar(int x,int y) {
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
