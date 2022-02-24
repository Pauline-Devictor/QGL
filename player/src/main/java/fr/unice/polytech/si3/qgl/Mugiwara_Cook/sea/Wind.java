package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

public class Wind {
    double orientation;
    double strength;

    public Wind(){
        //Json
    }

    public Wind(double orientation, double strength){
        this.orientation = orientation;
        this.strength = strength;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }


}
