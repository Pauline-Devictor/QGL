package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Spotter {

    NextRound nextRound;
    Checkpoint currentCheckpoint;
    @Getter
    ArrayList reefs;

    public Spotter(NextRound nextRound, Checkpoint currentCheckpoint) {
        this.nextRound = nextRound;
        this.currentCheckpoint = currentCheckpoint;
        setReefs();
    }

    public void setReefs() {
        reefs = new ArrayList<Reef>();
        for (VisibleEntity visibleEntity : nextRound.getVisibleEntities()) {
            if (visibleEntity.getType().equals("reef")) {
                reefs.add((Reef) visibleEntity);
            }
        }
    }

    public double getMapHeight() {
        return Math.abs(nextRound.getShip().getPosition().getY() - currentCheckpoint.getPosition().getY()) * 1.2;
    }

    public double getMapWidth() {
        return Math.abs(nextRound.getShip().getPosition().getX() - currentCheckpoint.getPosition().getX()) * 1.2;
    }

    // L'argument de cette méthode va définir la taille des carrés lors de l'échantillonage
    public List<List<Integer>> buildMap(int squareSize) {

        List<List<Integer>> map = new ArrayList<>();
        int x = 0;
        int y = 0;
        CollisionDetector collisionDetector = new CollisionDetector();
        for (double yReal = nextRound.getShip().getPosition().getY() + squareSize / 2; yReal - nextRound.getShip().getPosition().getY() < getMapHeight(); ) {
            List mapLine = new ArrayList<>();

            for (double xReal = nextRound.getShip().getPosition().getX() + squareSize / 2; xReal - nextRound.getShip().getPosition().getX() < getMapWidth(); ) {

                for (Object reef : reefs) {
                    if (collisionDetector.detectCollision(new Point(xReal, yReal), (Reef) reef)) {
                        //mapLine.add(1);
                        mapLine.add(new Node(x, y, xReal, yReal, true));
                    } else {
                        //mapLine.add(0);
                        mapLine.add(new Node(x,y,xReal,yReal,false));
                    }
                }
                xReal += squareSize;
                x++;
            }
            map.add(mapLine);
            yReal += squareSize;
            y++;
            x = 0;
        }
        return map;
    }
}
