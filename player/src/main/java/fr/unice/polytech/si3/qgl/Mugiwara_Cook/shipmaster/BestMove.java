package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves.Moves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves.OarMove;

import javax.swing.*;

public class BestMove {
    NextRound nextRound;
    AllPossibility allPossibility;

    public BestMove(AllPossibility allPossibility, NextRound nextRound) {
        this.nextRound = nextRound;
        this.allPossibility = allPossibility;
    }

    public void processing() {
        AllMove allMove = new AllMove();
        allMove.calculateMove(nextRound.getShip(),allPossibility);
        allMove.getDetail();
    }

    public Moves bestOne(AllMove allMove){
       return new OarMove(1,2,3,4,4,4);
    }

}
