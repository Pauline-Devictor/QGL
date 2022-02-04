package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

abstract public class VisibleEntity {
    String type;

    public VisibleEntity(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
