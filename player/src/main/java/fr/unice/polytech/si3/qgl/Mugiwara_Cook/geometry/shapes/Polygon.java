package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;

public class Polygon extends Shape{
    public final static String TYPE = "polygon";
    double orientation;
    Point[] vertices;

    public Polygon(double orientation,Point[] vertices) {
        super(TYPE);
        this.orientation = orientation;
        this.vertices = vertices;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }


}
