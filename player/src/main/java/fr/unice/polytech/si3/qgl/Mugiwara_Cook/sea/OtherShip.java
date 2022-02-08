package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes.Shape;

public class OtherShip extends VisibleEntity {
    public final static String TYPE = "ship";
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
