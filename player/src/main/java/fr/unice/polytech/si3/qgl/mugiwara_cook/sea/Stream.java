package fr.unice.polytech.si3.qgl.mugiwara_cook.sea;

import fr.unice.polytech.si3.qgl.mugiwara_cook.Position;
import fr.unice.polytech.si3.qgl.mugiwara_cook.shapes.Shape;

public class Stream {
    public final static String TYPE = "Stream";
    String type = TYPE;
    Position position;
    Shape shape;
    double strengh;

    public Stream(){
        //JSON
    }

    public Stream(Position position, Shape shape, double strengh){
        this.position = position;
        this.shape = shape;
        this.strengh = strengh;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getStrengh() {
        return strengh;
    }

    public void setStrengh(double strengh) {
        this.strengh = strengh;
    }


}
