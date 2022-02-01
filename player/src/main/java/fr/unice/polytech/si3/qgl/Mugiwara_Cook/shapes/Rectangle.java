package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes;

public class Rectangle extends Shape {
    public final static String TYPE = "Rectangle";
    double width;
    double height;
    double orientation;

    public Rectangle(double width,double height, double orientation) {
        super(TYPE);
        this.width = width;
        this.height = height;
        this.orientation = orientation;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

}
