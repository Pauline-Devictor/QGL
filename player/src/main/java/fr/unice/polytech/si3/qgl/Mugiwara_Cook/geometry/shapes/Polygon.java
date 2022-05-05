package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import lombok.Getter;
import lombok.Setter;

public class Polygon extends Shape {
    public static final String SHAPETYPE = "polygon";
    @Setter
    double orientation;
    @Setter
    @Getter
    Point[] vertices;

    public Polygon(double orientation, Point[] vertices) {
        super(SHAPETYPE);
        this.orientation = orientation;
        this.vertices = vertices;
    }
}
