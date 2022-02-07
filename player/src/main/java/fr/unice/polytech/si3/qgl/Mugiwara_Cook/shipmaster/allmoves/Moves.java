package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves;

abstract public class Moves {
    double x;
    double y;
    double orientation;

    public Moves(double x, double y, double orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}
