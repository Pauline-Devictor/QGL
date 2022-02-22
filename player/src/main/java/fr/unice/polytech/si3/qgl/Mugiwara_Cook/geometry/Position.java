package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry;

public class Position {
    double x;
    double y;
    double orientation;

    public Position(){
        //Jackson
    }
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

    /**
     * Calculate the distance between current item and another one
     * @param itemPosition Other item
     * @return distance
     */
    public double distance(Position itemPosition){
        return Math.sqrt(Math.pow(itemPosition.getX() - this.x, 2) + Math.pow(itemPosition.getY()-this.y, 2));
    }
}
