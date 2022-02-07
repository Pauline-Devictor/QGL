package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes.Shape;

public class Reef extends VisibleEntity {
    public final static String TYPE = "reef";
    Position position;
    Shape shape;

    public Reef(Position position, Shape shape){
        super(TYPE);
        this.position = position;
        this.shape = shape;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }


}
