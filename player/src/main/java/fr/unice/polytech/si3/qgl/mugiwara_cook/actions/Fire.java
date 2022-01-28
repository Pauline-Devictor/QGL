package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

public class Fire {
    public final static String TYPE = "FIRE";
    String type = TYPE;
    int sailorId;

    public Fire(){
        //Json
    }
    public Fire(int sailorId){
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
