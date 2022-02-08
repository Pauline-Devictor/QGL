package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves;

public class OarMove extends Moves {
    final int DECOMP = 1000;
    int oarLeft;
    int oarRight;
    double originalX;
    double originalY;
    int oarAll;
    double origialOrientation;
    double currentOrientation;
    float distance;

    public OarMove(double xOri, double yOri, double orientationOri, int oarLeft, int oarRight, int oarAll) {
        super(0, 0, 0);
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
        x = 0;
        y = 0;
        for (int i = 0; i < this.DECOMP; i++) {
            x += (this.distance / this.DECOMP) * Math.cos(((this.currentOrientation * i) / this.DECOMP) + this.origialOrientation);
            y += (this.distance / this.DECOMP) * Math.sin(((this.currentOrientation * i) / this.DECOMP) + this.origialOrientation);
        }
        this.x = this.originalX + x;
        this.y = this.originalY + y;
        this.orientation = this.origialOrientation + this.currentOrientation;
    }


    public void getDetail() {
        System.out.println("RAME: " + oarLeft + "][" + oarRight);
        super.getDetail();
    }

}
