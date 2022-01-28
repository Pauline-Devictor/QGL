package fr.unice.polytech.si3.qgl.mugiwara_cook.goal;

import fr.unice.polytech.si3.qgl.mugiwara_cook.sea.Checkpoint;

public class RegattaGoal extends Goal {
    public final static String MODE = "REGATTA";
    Checkpoint[] checkpoints;

    public RegattaGoal(Checkpoint[] checkpoints){
        super(MODE);
        this.checkpoints = checkpoints;
    }

    public Checkpoint[] getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(Checkpoint[] checkpoints) {
        this.checkpoints = checkpoints;
    }


}
