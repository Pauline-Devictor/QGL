package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

public class Lower_Sail {
    public final static String TYPE = "Lower_Sail";
    String type = TYPE;
    int sailorId;

    public Lower_Sail(){
        //Json
    }

    public Lower_Sail(int sailorId){
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
