package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes.Shape;

public class Stream extends VisibleEntity {
    public final static String TYPE = "Stream";
    Position position;
    Shape shape;
    double strength;

    public Stream(Position position, Shape shape, double strength){
        super(TYPE);
        this.position = position;
        this.shape = shape;
        this.strength = strength;
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

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strengh) {
        this.strength = strengh;
    }


}
