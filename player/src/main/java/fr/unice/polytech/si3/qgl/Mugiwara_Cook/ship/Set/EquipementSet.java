package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Set;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.*;

import java.util.Optional;

public class EquipementSet {
    private Oar leftoar;
    private Oar rightoar;
    private Canon leftcanon;
    private Canon rightcanon;
    private Sail sail;
    private Watch watch;
    private Rudder rudder;
    private Ship ship;
    private Sailor[] sailors;


    public EquipementSet(Ship ship,Sailor[] sailors){
        this.sailors=sailors;
        this.ship=ship;
    }


    public Oar getLeftoar() {
        return leftoar;
    }

    public void setLeftoar(Oar leftoar) {
        this.leftoar = leftoar;
    }

    public Oar getRightoar() {
        return rightoar;
    }

    public void setRightoar(Oar rightoar) {
        this.rightoar = rightoar;
    }

    public Canon getLeftcanon() {
        return leftcanon;
    }

    public void setLeftcanon(Canon leftcanon) {
        this.leftcanon = leftcanon;
    }

    public Canon getRightcanon() {
        return rightcanon;
    }

    public void setRightcanon(Canon rightcanon) {
        this.rightcanon = rightcanon;
    }

    public Sail getSail() {
        return sail;
    }

    public void setSail(Sail sail) {
        this.sail = sail;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public Rudder getRudder() {
        return rudder;
    }

    public void setRudder(Rudder rudder) {
        this.rudder = rudder;
    }




}
