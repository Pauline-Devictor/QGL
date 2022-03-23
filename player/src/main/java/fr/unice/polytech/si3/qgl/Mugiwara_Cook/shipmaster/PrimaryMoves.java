package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Lift_Sail;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Lower_Sail;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Turn;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Sail;

import java.util.ArrayList;
import java.util.List;

public class PrimaryMoves {

    InitGame initGame;
    ActionJSON actionJSON;

    public PrimaryMoves(InitGame initGame, ActionJSON actionJson) {
        this.initGame = initGame;
        this.actionJSON = actionJson;
    }


    public void primaryMoveOar(int leftOar, int rightOar) {
        for (int i = 0; i < leftOar; i++) {
            actionJSON.addAction(new Oar(initGame.getUsableSailorLeft().get(i).getId()));
        }
        for (int i = 0; i < rightOar; i++) {
            actionJSON.addAction(new Oar(initGame.getUsableSailorRight().get(i).getId()));
        }
    }

    public void primaryMoveTurn(double angle) {
        if (initGame.getUsableSailorRudder() != null)
            actionJSON.addAction(new Turn(initGame.getUsableSailorRudder().getId(), angle));
    }

    public void primaryMoveSail(int nbsails) {
        List<Equipment> sails = initGame.getShip().getUsableSails();
        List<Sailor> sailorsAssignedToSail = initGame.allSailorAssignedTo("sail");
        List<Sailor> UsableSailorsForSail =initGame.getUsableSailorSail();
        System.out.println(UsableSailorsForSail.size());
        int nbSailsOpenned = 0;
        for (Equipment s : sails) {
            s = (Sail) s;
            if (((Sail) s).isOpenned()) nbSailsOpenned++;
        }
        setUpSails(nbsails, sailorsAssignedToSail, nbSailsOpenned,UsableSailorsForSail);
    }

    public void setUpSails(int nbsails, List<Sailor> sailorsAssignedToSail, int nbSailsOpenned,List<Sailor> usableSailorsToSail) {
        int count = 0;
        while (nbSailsOpenned != nbsails || count > sailorsAssignedToSail.size()) {
            if (nbSailsOpenned > nbsails) {
                Sail sail = (Sail) sailorsAssignedToSail.get(count).getEquipment();
                if (sail.isOpenned()) {
                    actionJSON.addAction(new Lower_Sail(usableSailorsToSail.get(count).getId()));
                    sail.setOpenned(false);
                    nbSailsOpenned--;

                }
            }
            if (nbSailsOpenned < nbsails) {
                Sail sail = (Sail) sailorsAssignedToSail.get(count).getEquipment();
                System.out.println("on passe la ");
                if (!sail.isOpenned()) {
                    System.out.println("ici aussi");
                    System.out.println(initGame.getUsableSailorSail().size());
                    actionJSON.addAction(new Lift_Sail(usableSailorsToSail.get(count).getId()));
                    sail.setOpenned(true);
                    nbSailsOpenned++;

                }
            }
            count++;
        }
    }
}


