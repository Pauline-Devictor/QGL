package fr.unice.polytech.si3.qgl.mugiwara_cook.sea;

import fr.unice.polytech.si3.qgl.mugiwara_cook.Position;
import fr.unice.polytech.si3.qgl.mugiwara_cook.shapes.Shape;

public class OtherShip extends visibleEntity {
    public final static String TYPE = "Ship";
    int life;
    Position position;
    Shape shape;


    public OtherShip(int life, Position position, Shape shape){
        super(TYPE);
        this.life = life;
        this.position = position;
        this.shape = shape;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
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
