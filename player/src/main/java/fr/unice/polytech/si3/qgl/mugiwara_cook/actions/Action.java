package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

abstract public class Action {
    String type;

    public Action(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
