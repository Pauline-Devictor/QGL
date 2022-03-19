package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import lombok.Getter;
import lombok.Setter;

public class Polygon extends Shape {
    public final static String TYPE = "polygon";
    @Getter
    @Setter
    double orientation;
    @Getter
    @Setter
    Point[] vertices;

    public Polygon(double orientation, Point[] vertices) {
        super(TYPE);
        this.orientation = orientation;
        this.vertices = vertices;
    }
}
