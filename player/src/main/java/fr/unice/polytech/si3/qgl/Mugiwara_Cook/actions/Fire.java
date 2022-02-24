package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

public class Fire extends Action {
    public final static String TYPE = "FIRE";
    int sailorId;

    public Fire(int sailorId){
        super(TYPE);
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
