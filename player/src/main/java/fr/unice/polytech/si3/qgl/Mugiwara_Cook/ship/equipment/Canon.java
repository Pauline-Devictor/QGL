package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Canon extends Equipment {
    public final static String TYPE = "canon";
    boolean loaded;
    double angle;
    @JsonIgnore
    boolean used = false;

    public Canon(int x, int y,boolean loaded,double angle) {
        super(TYPE,x,y);
        this.loaded = loaded;
        this.angle = angle;
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
