package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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
    public boolean sailorIsAllowedToMove(int xdistance, int ydistance) {
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
    public boolean sailorIsNotGoingToFallInTheSea(int sailorId, int xdistance, int ydistance) {
        if (sailorIsNotGoingToFallInTheSeaX(sailorId, xdistance) && sailorIsNotGoingToFallInTheSeaY(sailorId, ydistance)) {
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
    public boolean sailorIsNotGoingToFallInTheSeaX(int sailorId, int xdistance) {
        int xmaxship = ship.getDeck().getLength()-1;
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
    public boolean sailorIsNotGoingToFallInTheSeaY(int sailorId, int ydistance) {
        int ymaxship = ship.getDeck().getWidth()-1;
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

    public List<Integer> getSailorLeft(){
        return sailorLeft;
    }

    public List<Integer> getSailorRight(){
        return sailorRight;
    }

    // On aura peut-être un problème avec les limites de déplacements des marins
    // Cette méthodes reste à revoir plus tard car elle compare les marins aux râmes dans leur ordre d'apparition dans sailors
    // Laisser la partie commentée pour plus tard

    public ArrayList<Action> sailorsMoveToOars(){
        ArrayList<Action> moves = new ArrayList<>();
        ArrayList<Oar> oars = ship.getOars();
        /**for (int i = 0; i < sailors.length; i++){
            if(sailors[i].getY()+sailors[i].HowManyCaseFarFromOarY(sailors[i].findClosestOarFromSailor(oars))==0){
                sailorLeft.add(sailors[i].getId());
            }else{
                sailorRight.add(sailors[i].getId());
            }
            Moving sailorMove = new Moving(sailors[i].getId(),sailors[i].findClosestOarFromSailor(oars).getX(),sailors[i].findClosestOarFromSailor(oars).getY());
            oars.remove(sailors[i].findClosestOarFromSailor(oars));
            moves.add(sailorMove);
        }*/
        int i=0;
        while(i<sailors.length && oars.size()>=1){

            if(sailors[i].getY()+sailors[i].howManyCaseFarFromOarY(sailors[i].findClosestOarFromSailor(oars))==0){
                sailorLeft.add(sailors[i].getId());
            }else{
                sailorRight.add(sailors[i].getId());
            }
            Moving sailorMove = new Moving(sailors[i].getId(),sailors[i].findClosestOarFromSailor(oars).getX()-sailors[i].getX(),sailors[i].findClosestOarFromSailor(oars).getY()-sailors[i].getY());
            oars.remove(sailors[i].findClosestOarFromSailor(oars));
            moves.add(sailorMove);
            i++;
        }
        return moves;
    }
}
