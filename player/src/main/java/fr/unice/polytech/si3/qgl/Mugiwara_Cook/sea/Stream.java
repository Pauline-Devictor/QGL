package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;

public class Stream extends VisibleEntity {
    public final static String TYPE = "stream";
    double strength;

    public Stream(Position position, Shape shape, double strength){
        super(TYPE,shape,position);
        this.strength = strength;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strengh) {
        this.strength = strengh;
    }


}
