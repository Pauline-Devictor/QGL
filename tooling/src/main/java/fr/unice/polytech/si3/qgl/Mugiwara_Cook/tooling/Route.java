package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Route {
    ArrayList<Position> positionArrayList;
    ArrayList<Sailor[]> sailorsArrayList;
    Checkpoint[] checkpoints;

    public Route(Checkpoint[] checkpoints) {
        this.positionArrayList = new ArrayList<>();
        this.checkpoints = checkpoints;
    }

    public ArrayList<Position> getPositionArrayList() {
        return this.positionArrayList;
    }

    public void addPosition(Position position) {
        this.positionArrayList.add(position);
    }

    public void addPosition(List<Position> position) {
        position.forEach(p-> this.positionArrayList.add(p));
    }

    public ArrayList<Sailor[]> getSailorsArrayList() {
        return sailorsArrayList;
    }

    public void addSailorsArrayList(Sailor[] sailorsArrayList) {
        this.sailorsArrayList.add(sailorsArrayList);
    }

    public Checkpoint[] getCheckpoints() {
        return checkpoints;
    }
}
