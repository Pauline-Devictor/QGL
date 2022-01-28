package fr.unice.polytech.si3.qgl.mugiwara_cook.sea;

import fr.unice.polytech.si3.qgl.mugiwara_cook.Position;
import fr.unice.polytech.si3.qgl.mugiwara_cook.shapes.Shape;

public class Checkpoint {
    Position position;
    Shape shape;

    public Checkpoint(){
        //Json
    }

    public Checkpoint(Position position, Shape shape){
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
