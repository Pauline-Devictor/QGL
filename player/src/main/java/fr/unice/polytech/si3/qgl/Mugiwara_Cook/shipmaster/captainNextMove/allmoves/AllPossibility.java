package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;

import java.util.ArrayList;
import java.util.List;

public class AllPossibility {
    InitGame initGame;

    int nbMaxOar;
    List<double[]> listOarPossibility = new ArrayList<>();


    public AllPossibility(InitGame initGame) {
        this.initGame = initGame;
        this.nbMaxOar = initGame.getShip().getNbOars();
    }

    public void oarPossibility(boolean rudder) {
        listOarPossibility.clear();
        int rudderTrue = 0;
        if (rudder) rudderTrue = 1;

        int oarLeft = initGame.getShip().getUsableOarsLeft().size();
        int oarRight = initGame.getShip().getUsableOarsRight().size();

        for (int i = oarLeft; i >= 0; i--) {
            for (int k = oarRight; k >= 0; k--) {
                if (i + k <= initGame.getSailors().length && i + k != 0) {
                    double angle2 = this.angle(i, k);
                    listOarPossibility.add(new double[]{(int) (angle2 - (Math.PI / 4)) * rudderTrue, angle2, (int) (angle2 + (Math.PI / 4)) * rudderTrue, i, k});
                }
            }
        }
    }

    public double angle(int oarLeft, int oarRight) {
        return (Math.PI / 2) / (initGame.getShip().getNbOars() / 2) * (oarRight - oarLeft);
    }

    public List<double[]> getAllPossibility() {
        return listOarPossibility;
    }

    public void getDetail(){
        listOarPossibility.forEach(i->
        {
            System.out.print(i[0]);
            System.out.print(", ");
            System.out.print(i[1]);
            System.out.print(", ");
            System.out.print(i[2]);
            System.out.print(", oars: ");
            System.out.print(i[3]);
            System.out.print(", ");
            System.out.println(i[4]);

        });
    }

}
