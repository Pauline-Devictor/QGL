package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public class Position {
    @Getter
    @Setter
    double x;
    @Getter
    @Setter
    double y;
    @Getter
    @Setter
    double orientation;

    public Position() {
        //Jackson
    }

    public Position(double x, double y, double orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    /**
     * Calculate the distance between current item and another one
     *
     * @param itemPosition Other item
     * @return distance
     */
    public double distance(Position itemPosition) {
        return Math.sqrt(Math.pow(itemPosition.getX() - this.x, 2) + Math.pow(itemPosition.getY() - this.y, 2));
    }

    @JsonIgnore
    public String getListPosition() {
        return "[" + this.getX() + "," + this.getY() + "," + this.getOrientation() + "]";
    }
}
