package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;

public class OtherShip extends VisibleEntity{
    int life;
    public static final String ENTITYTYPE = "ship";

    protected OtherShip(Position position, Shape shape, int life) {
        super(ENTITYTYPE, shape, position);
        this.life=life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
