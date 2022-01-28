package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

public class Use_Watch {
    public final static String TYPE = "USE_WATCH";
    String type = TYPE;
    int sailorId;

    public Use_Watch(){
        //Json
    }

    public Use_Watch(int sailorId){
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
