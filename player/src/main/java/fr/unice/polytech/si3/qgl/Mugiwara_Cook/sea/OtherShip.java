package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;

public class OtherShip extends VisibleEntity {
    public final static String TYPE = "ship";
    int life;


    public OtherShip(int life, Position position, Shape shape){
        super(TYPE,shape,position);
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
