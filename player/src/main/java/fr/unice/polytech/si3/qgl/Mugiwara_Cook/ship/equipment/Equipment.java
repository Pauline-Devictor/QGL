package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

abstract public class Equipment {
    protected String type;

    public Equipment(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}