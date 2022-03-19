package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import lombok.Getter;
import lombok.Setter;

public class Rectangle extends Shape {
    public final static String TYPE = "rectangle";
    @Getter
    @Setter
    double width;
    @Getter
    @Setter
    double height;
    @Getter
    @Setter
    double orientation;

    public Rectangle(double width, double height, double orientation) {
        super(TYPE);
        this.width = width;
        this.height = height;
        this.orientation = orientation;
    }
}
