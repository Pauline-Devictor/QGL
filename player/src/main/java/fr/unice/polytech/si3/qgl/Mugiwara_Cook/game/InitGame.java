package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Sail;

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
        return Arrays.asList(this.sailors).stream()
                .filter(sailor -> sailor.getEquipment() != null)
                .filter(sailor -> sailor.getEquipment().getType().equals("oar"))
                .filter(sailor -> sailor.getEquipment().getY() == 0)
                .filter(sailor -> sailor.onIsAssignEquipment() == true)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Sailor> getUsableSailorRight() {
        return Arrays.asList(this.sailors).stream()
                .filter(sailor -> sailor.getEquipment() != null)
                .filter(sailor -> sailor.getEquipment().getType().equals("oar"))
                .filter(sailor -> sailor.getEquipment().getY() == (this.ship.getDeck().getWidth() - 1))
                .filter(sailor -> sailor.onIsAssignEquipment() == true)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public boolean allSailorIsOnAssign() {
        for (int i = 0; i < sailors.length; i++) {
            if (sailors[i].getEquipment() != null)
                if (!(sailors[i].onIsAssignEquipment()))
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
            return Arrays.asList(this.sailors).stream()
                    .filter(sailor -> sailor.getEquipment() != null)
                    .filter(sailor -> sailor.getEquipment().getType().equals("rudder"))
                    .filter(sailor -> sailor.onIsAssignEquipment() == true)
                    .collect(Collectors.toList()).get(0);
        } else
            return null;
    }

    @JsonIgnore
    public List<Sailor> getUsableSailorSail() {
        return Arrays.asList(this.sailors).stream()
                .filter(sailor -> sailor.getEquipment() != null)
                .filter(sailor -> sailor.getEquipment().getType().equals("sail"))
                .filter(sailor -> sailor.onIsAssignEquipment() == true)
                .collect(Collectors.toList());
    }


    @JsonIgnore
    public boolean sailorRudder() {
        for (int i = 0; i < sailors.length; i++) {
            if (sailors[i].getEquipment() != null && sailors[i].getEquipment().getType().equals("rudder"))
                if (sailors[i].onIsAssignEquipment())
                    return true;
        }
        return false;
    }

    @JsonIgnore
    public Sailor sailorSail(Sail sail) {
        List<Sailor> sailorsSail = new ArrayList<>();
        for (int i = 0; i < sailors.length; i++) {
            if (sailors[i].getEquipment() != null && sailors[i].getEquipment().getType().equals("sail"))
                return sailors[i];
            return sailors[i];
        }
        return null;
    }


    public boolean isSailorOnTheEquipment(Equipment equipment, Sailor sailor) {
        if (sailor.getX() == equipment.getX() && sailor.getY() == equipment.getY()) return true;
        return false;
    }
}
