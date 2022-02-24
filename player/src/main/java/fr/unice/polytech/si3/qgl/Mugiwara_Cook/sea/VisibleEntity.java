package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;

abstract public class VisibleEntity {
    String type;
    Shape shape;
    Position position;

    public VisibleEntity(String type,Shape shape, Position position){
        this.type = type;
        this.shape = shape;
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
