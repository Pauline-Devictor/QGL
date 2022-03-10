package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible;

import java.util.ArrayList;
import java.util.List;

public class AngleOption {
    double angle;
    int delta; //NBoarRight - NBoarLeft

    public static List<AngleOption> creationOptionFromOarCount(int leftCount, int rightCount, int oarTotal) {
        List<AngleOption> angleOptionList = new ArrayList<>();
//        System.out.println(this.initGame.getShip().getUsableOarsLeft().size() + " : " + this.initGame.getShip().getUsableOarsRight().size());
        for (int i = leftCount; i > -1; i--) {
//            System.out.println(this.initGame.getShip().getUsableOarsRight().size() + "-" + i);
            angleOptionList.add(angle(rightCount, i, oarTotal));
        }
//        System.out.println("------");
        for (int i = rightCount - 1; i > -1; i--) {
//            System.out.println(i + "-" + this.initGame.getShip().getUsableOarsLeft().size());
            angleOptionList.add(angle(i, leftCount, oarTotal));
        }
        return angleOptionList;
    }

    public AngleOption(double angle, int delta) {
        this.angle = angle;
        this.delta = delta;
    }

    public static AngleOption angle(int oarLeft, int oarRight, int oarTotal) {
        return new AngleOption((Math.PI / 2) / ((oarTotal) / 2) * (oarRight - oarLeft), oarRight - oarLeft);
    }
    public void getDetail() {
        System.out.println("Angle: " + this.angle + " lier a un delta de: " + this.delta);
    }

    public double getAngle() {
        return angle;
    }

    public int getDelta() {
        return delta;
    }
}
