package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

public class Reload {
    public final static String TYPE = "RELOAD";
    String type = TYPE;
    int sailorId;

    public Reload() {
        //JSON
    }

    public Reload(int sailorId){
        this.sailorId = sailorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSailorId() {
        return sailorId;
    }

    public void setSailorId(int sailorId) {
        this.sailorId = sailorId;
    }


}
