package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

abstract public class visibleEntity {
    String type;

    public visibleEntity(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
