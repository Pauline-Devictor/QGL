package fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;

public class RegattaGoal extends Goal {
    public final static String MODE = "REGATTA";
    public String mode = MODE;
    public Checkpoint[] checkpoints;

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
