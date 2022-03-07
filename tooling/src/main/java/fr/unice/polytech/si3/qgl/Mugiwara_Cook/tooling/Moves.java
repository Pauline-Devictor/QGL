package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

import java.util.ArrayList;

public class Moves {
    final int DECOMP = 1000;

    double orientation;
    int oarLeft;
    int oarRight;
    double originalX;
    double originalY;
    double origialOrientation;
    double currentFinalOrientation;
    float distance;

    //Simulateur
    ArrayList<Position> positionArrayList;

    public Moves(Ship ship, int[] oars) {
        this.oarLeft = oars[0];
        this.oarRight = oars[1];
        this.origialOrientation = ship.getPosition().getOrientation();
        this.originalX = ship.getPosition().getX();
        this.originalY = ship.getPosition().getY();

        positionArrayList = new ArrayList<>();

        this.distance(ship.getNbOars());
        this.angle(ship.getNbOars());
        this.newPosition();
    }

    public void angle(int oarAll) {
        this.currentFinalOrientation = (Math.PI / 2) / (oarAll / 2) * (this.oarRight - this.oarLeft);
    }

    public void distance(int oarAll) {
        this.distance = (165 * (this.oarLeft + this.oarRight)) / oarAll;
    }

    public void newPosition() {
        for (int i = 0; i < this.DECOMP; i++) {
            this.originalX += (this.distance / this.DECOMP) * Math.cos(((this.currentFinalOrientation * i) / this.DECOMP) + this.origialOrientation);
            this.originalY += (this.distance / this.DECOMP) * Math.sin(((this.currentFinalOrientation * i) / this.DECOMP) + this.origialOrientation);
            positionArrayList.add(new Position(this.originalX, this.originalY, this.origialOrientation + (this.currentFinalOrientation * i) / this.DECOMP));
        }

        this.orientation = this.origialOrientation + this.currentFinalOrientation;
    }

    public int[] getOar() {
        return new int[]{oarLeft, oarRight};
    }

    public double getX() {
        return this.originalX;
    }

    public double getY() {
        return this.originalY;
    }

    public double getOrientation() {
        return orientation;
    }

    //Simulateur
    public ArrayList<Position> getDetailPosition() {
        return positionArrayList;
    }

    public boolean inCheckpoint(Checkpoint currentCheckpoint) {
        return new Position(this.originalX,this.originalY,this.orientation).distance(currentCheckpoint.getPosition())
                <= ((Circle) currentCheckpoint.getShape()).getRadius();
    }

}
