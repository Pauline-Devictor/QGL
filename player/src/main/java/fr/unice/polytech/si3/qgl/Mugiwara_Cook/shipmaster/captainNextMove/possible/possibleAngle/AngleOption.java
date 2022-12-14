package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class AngleOption {
    @Getter
    @Setter
    double angle;
    @Getter
    int delta; //NBoarRight - NBoarLeft


    /**
     * Renvois une liste de couple angle, delta
     *
     * @param leftCount  nombre de rames à gauche
     * @param rightCount nombre de rames à droite
     * @param oarTotal   nombre de rame total
     * @return tous les angles possibles (associé à un delta)en fonction des compositions de rames
     */
    public static List<AngleOption> creationOptionFromOarCount(int leftCount, int rightCount, int oarTotal) {
        List<AngleOption> angleOptionList = new ArrayList<>();
        for (int i = leftCount; i > -1; i--) {
            angleOptionList.add(angle(rightCount, i, oarTotal));
        }
        for (int i = rightCount - 1; i > -1; i--) {
            angleOptionList.add(angle(i, leftCount, oarTotal));
        }
        return angleOptionList;
    }

    public AngleOption(double angle, int delta) {
        this.angle = angle;
        this.delta = delta;
    }

    /**
     * Donne le couple angle delta en fonction de la composition de rames (fait l'hypotese qu'il y a toujours le meme nombre de rame a droite et gauche)
     *
     * @param oarLeft  nombre de rames à gauche
     * @param oarRight nombre de rames à droite
     * @param oarTotal nombre de rames total
     * @return le couple angle delta
     */
    public static AngleOption angle(int oarLeft, int oarRight, int oarTotal) {
        return new AngleOption((Math.PI / 2) / (((double) oarTotal) / 2) * (oarRight - oarLeft), oarRight - oarLeft);
    }

}
