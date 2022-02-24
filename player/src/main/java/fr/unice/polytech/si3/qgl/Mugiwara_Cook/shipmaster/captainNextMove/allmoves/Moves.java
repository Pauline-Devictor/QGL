package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves;

public class Moves {
    double x;
    double y;
    double orientation;
    final int DECOMP = 1000;
    int oarLeft;
    int oarRight;
    double originalX;
    double originalY;
    int oarAll;
    double origialOrientation;
    double currentOrientation;
    float distance;

    public Moves(double xOri, double yOri, double orientationOri, int oarLeft, int oarRight, int oarAll) {
        this.oarLeft = oarLeft;
        this.oarRight = oarRight;
        this.oarAll = oarAll;
        this.origialOrientation = orientationOri;
        this.originalX = xOri;
        this.originalY = yOri;

        this.distance();
        this.angle();
        this.newPosition();
    }

    public void angle() {
        this.currentOrientation = (Math.PI / 2) / (this.oarAll / 2) * (this.oarRight - this.oarLeft);
    }

    public void distance() {
        this.distance = (165 * (this.oarLeft + this.oarRight)) / this.oarAll;
    }

    public void newPosition() {
        this.x = 0;
        this.y = 0;
        for (int i = 0; i < this.DECOMP; i++) {
            this.x += (this.distance / this.DECOMP) * Math.cos(((this.currentOrientation * i) / this.DECOMP) + this.origialOrientation);
            this.y += (this.distance / this.DECOMP) * Math.sin(((this.currentOrientation * i) / this.DECOMP) + this.origialOrientation);
        }
        this.x = this.originalX + x;
        this.y = this.originalY + y;
        this.orientation = this.origialOrientation + this.currentOrientation;
    }

    public int[] getOar() {
        return new int[]{oarLeft, oarRight};
    }

    public void getDetail() {
        //System.out.println("RAME: " + oarLeft + "][" + oarRight);
        //System.out.println("x: " + x + ", y: " + y + " et l'orientation: " + orientation);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getOrientation() {
        return orientation;
    }


}
