package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Captain {
    Action[] actions;
    Ship ship;
    Sailor[] sailors;

    List<Integer> sailorLeft = new ArrayList<>();
    List<Integer> sailorRight = new ArrayList<>();

    public Captain(Ship ship, Sailor[] sailors) {
        this.sailors = sailors;
        this.ship = ship;
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

    /**
     * @param xdistance le nombre de déplacement en x
     * @param ydistance le nombre de déplacement en y
     * @return un boolean qui dit vrai s'il respect les conditions, faux sinon
     */
    public boolean SailorIsAllowedToMove(int xdistance, int ydistance) {
        if (abs(xdistance) + abs(ydistance) <= 5) return true;
        return false;
    }

    /**
     * Permet de savoir si le sailor ne va pas tomber dans l'eau en utiliser une configuration de déplacement
     *
     * @param sailorId  le sailorid du sailor qui va se deplacer
     * @param xdistance distance à parcourir en x
     * @param ydistance distance à parcourir en y
     * @return true s'il ne tombe pas sinon false
     */
    public boolean SailorIsNotGoingToFallInTheSea(int sailorId, int xdistance, int ydistance) {
        if (SailorIsNotGoingToFallInTheSeaX(sailorId, xdistance) && SailorIsNotGoingToFallInTheSeaY(sailorId, ydistance)) {
            return true;
        }
        return false;

    }

    /**
     * Permet de savoir si le sailor ne va pas tomber dans l'eau en utiliser une configuration de déplacement en X
     *
     * @param sailorId  le sailorid du sailor qui va se deplacer
     * @param xdistance distance à parcourir en x
     * @return true s'il ne tombe pas sinon false
     */
    public boolean SailorIsNotGoingToFallInTheSeaX(int sailorId, int xdistance) {
        int xmaxship = ship.getDeck().getLength();
        int xsailor = findASailorWithId(sailorId).getX();
        if ((0 <= xsailor + xdistance && xsailor + xdistance <= xmaxship)) {
            return true;
        }
        return false;
    }


    /**
     * Permet de savoir si le sailor ne va pas tomber dans l'eau en utiliser une configuration de déplacement en Y
     *
     * @param sailorId  le sailorid du sailor qui va se deplacer
     * @param ydistance distance à parcourir en y
     * @return true s'il ne tombe pas sinon false
     */
    public boolean SailorIsNotGoingToFallInTheSeaY(int sailorId, int ydistance) {
        int ymaxship = ship.getDeck().getWidth();
        int ysailor = findASailorWithId(sailorId).getY();
        if ((0 <= ysailor + ydistance && ysailor + ydistance <= ymaxship)) {
            return true;
        }
        return false;
    }

    /**
     * retrouve un sailor parmis la liste des sailor grace à son id
     *
     * @param sailorId
     * @return le sailor qu'il fallait trouver
     */
    public Sailor findASailorWithId(int sailorId) {
        Sailor sailor = new Sailor();
        for (Sailor e : sailors) {
            if (e.getId() == sailorId) sailor = e;
        }
        return sailor;
    }

    /**
     * "attribut" une rame à chaque marin, nom à changer et methode a modifier dans l'avenir.
     *
     * @return La list des mouvements de marin
     */
    public ArrayList<Action> sailorsFollowMyCommand() {
        ArrayList<Action> moves = new ArrayList<>();
        ArrayList<Oar> oars = ship.getOars();
        for (int i = 0; i < sailors.length; i++) {
            Sailor sailor = sailors[i];
            int x = sailor.HowManyCaseFarFromOarX(oars.get(i));
            int y = sailor.HowManyCaseFarFromOarY(oars.get(i));
            if(sailor.getY()+y==0){
                sailorLeft.add(sailor.getId());
            }else{
                sailorRight.add(sailor.getId());
            }
            Moving move = new Moving(sailor.getId(), x, y);
            moves.add(move);
        }
        return moves;
    }

    public List<Integer> getSailorLeft(){
        return sailorLeft;
    }

    public List<Integer> getSailorRight(){
        return sailorRight;
    }

}
