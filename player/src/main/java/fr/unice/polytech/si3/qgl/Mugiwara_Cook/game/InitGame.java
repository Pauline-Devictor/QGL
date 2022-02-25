package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InitGame {
    Goal goal;
    Ship ship;
    Sailor[] sailors;
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Sailor[] getSailors() {
        return sailors;
    }

    public void setSailors(Sailor[] sailors) {
        this.sailors = sailors;
    }

    public int getShipCount() {
        return shipCount;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }

    @JsonIgnore
    public List<Sailor> getUsableSailorLeft() {
        return Arrays.asList(this.sailors).stream()
                .filter(sailor -> sailor.getOar().getY() == 0)
                .filter(sailor -> sailor.onIsAssignOar() == true)
                .collect(Collectors.toList());
    }
    @JsonIgnore
    public List<Sailor> getUsableSailorRight() {
        return Arrays.asList(this.sailors).stream()
                .filter(sailor -> sailor.getOar().getY() == (this.ship.getDeck().getWidth() - 1))
                .filter(sailor -> sailor.onIsAssignOar() == true)
                .collect(Collectors.toList());
    }
}
