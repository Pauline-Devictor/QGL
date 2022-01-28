package fr.unice.polytech.si3.qgl.mugiwara_cook.sea;

import fr.unice.polytech.si3.qgl.mugiwara_cook.Position;
import fr.unice.polytech.si3.qgl.mugiwara_cook.shapes.Shape;

public class OtherShip {
    public final static String TYPE = "Ship";
    String type = TYPE;
    int life;
    Position position;
    Shape shape;

    public OtherShip(){
        //Json
    }

    public OtherShip(int life, Position position, Shape shape){
        this.life = life;
        this.position = position;
        this.shape = shape;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
