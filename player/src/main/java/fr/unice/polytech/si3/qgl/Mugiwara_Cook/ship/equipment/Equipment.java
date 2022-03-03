package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;

import static java.lang.Math.abs;

abstract public class Equipment {
    protected String type;
    protected int x;
    protected int y;

    @JsonIgnore
    boolean used = false;

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Sailor getSailor() {
        return sailor;
    }

    public void setSailor(Sailor sailor) {
        this.sailor = sailor;
    }

    @JsonIgnore
    Sailor sailor = null;

    public Equipment(String type,int x,int y){
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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


    public Sailor findClosestSailorWithOutAssignEquipment(Sailor[] sailors) {
        Sailor sailor=new Sailor();
        return sailor;
    }


}
