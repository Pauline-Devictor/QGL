package fr.unice.polytech.si3.qgl.mugiwara_cook;

public class Position {
    double x;
    double y;
    double orientation;

    public Position(double x, double y, double orientation){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public double getX() {return x;}

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

}
