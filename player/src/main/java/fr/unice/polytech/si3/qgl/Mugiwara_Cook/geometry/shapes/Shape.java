package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

abstract public class Shape {
    String type;

    public Shape(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
