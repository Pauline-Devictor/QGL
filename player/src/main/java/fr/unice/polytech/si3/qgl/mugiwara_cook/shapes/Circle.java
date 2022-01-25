package fr.unice.polytech.si3.qgl.mugiwara_cook.shapes;

public class Circle extends Shape{
    public final static String TYPE = "Circle";
    double radius;

    public Circle(double radius) {
        super(TYPE);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
