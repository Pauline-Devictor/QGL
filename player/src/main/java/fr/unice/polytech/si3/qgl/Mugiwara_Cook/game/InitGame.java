package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InitGame {
    @Getter
    @Setter
    Goal goal;
    @Getter
    @Setter
    Ship ship;
    @Getter
    @Setter
    Sailor[] sailors;
    @Getter
    @Setter
    int shipCount;

    public InitGame() {
        //Json
    }

    public InitGame(Goal goal, Ship ship, Sailor[] sailors, int shipCount) {
        this.goal = goal;
        this.ship = ship;
        this.sailors = sailors;
        this.shipCount = shipCount;
    }

    @JsonIgnore
    public List<Sailor> getUsableSailorLeft() {
        return Arrays.stream(this.sailors)
                .filter(sailor -> sailor.getEquipment() != null)
                .filter(sailor -> sailor.getEquipment().getType().equals("oar"))
                .filter(sailor -> sailor.getEquipment().getY() == 0)
                .filter(Sailor::onIsAssignEquipment).toList();

    }

    @JsonIgnore
    public List<Sailor> getUsableSailorRight() {
        return Arrays.stream(this.sailors)
                .filter(sailor -> sailor.getEquipment() != null)
                .filter(sailor -> sailor.getEquipment().getType().equals("oar"))
                .filter(sailor -> sailor.getEquipment().getY() == (this.ship.getDeck().getWidth() - 1))
                .filter(Sailor::onIsAssignEquipment).toList();
    }

    @JsonIgnore
    public boolean allSailorIsOnAssign() {
        for (int i = 0; i < sailors.length; i++) {
            if (sailors[i].getEquipment() != null && !(sailors[i].onIsAssignEquipment()))
                    return false;
        }
        return true;
    }

    @JsonIgnore
    public List<Sailor> allSailorAssignedTo(String equipment) {
        List<Sailor> sailorsAssignedToAnSpecificEquipement = new ArrayList<>();
        for (int i = 0; i < sailors.length; i++) {
            if (sailors[i].getEquipment().getType().equals(equipment)) {
                sailorsAssignedToAnSpecificEquipement.add(sailors[i]);
            }
        }
        return sailorsAssignedToAnSpecificEquipement;
    }

    @JsonIgnore
    public Sailor getUsableSailorRudder() {
        if (this.sailorRudder()) {
            return Arrays.stream(this.sailors)
                    .filter(sailor -> sailor.getEquipment() != null)
                    .filter(sailor -> sailor.getEquipment().getType().equals("rudder"))
                    .filter(Sailor::onIsAssignEquipment).toList().get(0);
        } else
            return null;
    }

    @JsonIgnore
    public List<Sailor> getUsableSailorSail() {
        return Arrays.stream(this.sailors)
                .filter(sailor -> sailor.getEquipment() != null)
                .filter(sailor -> sailor.getEquipment().getType().equals("sail"))
                .filter(Sailor::onIsAssignEquipment).toList();
    }


    @JsonIgnore
    public boolean sailorRudder() {
        for (int i = 0; i < sailors.length; i++) {
            if (sailors[i].getEquipment() != null && sailors[i].getEquipment().getType().equals("rudder") && sailors[i].onIsAssignEquipment() )
                    return true;
        }
        return false;
    }
}
