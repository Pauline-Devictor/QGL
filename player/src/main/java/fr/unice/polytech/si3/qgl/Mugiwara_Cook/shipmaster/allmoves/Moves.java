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


    public void getDetail() {
        //System.out.println("x: " + x + ", y: " + y + " et l'orientation: " + orientation);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getOrientation() {
        return orientation;
    }

}
