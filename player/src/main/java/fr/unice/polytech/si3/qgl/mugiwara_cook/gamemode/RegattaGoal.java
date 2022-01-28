package fr.unice.polytech.si3.qgl.mugiwara_cook.gamemode;

import fr.unice.polytech.si3.qgl.mugiwara_cook.Checkpoint;

public class RegattaGoal {
    String mode = "REGATTA";
    Checkpoint[] checkpoints;

    public RegattaGoal() {
        //Json
    }

    public RegattaGoal(Checkpoint[] checkpoints){
        this.checkpoints = checkpoints;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Checkpoint[] getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(Checkpoint[] checkpoints) {
        this.checkpoints = checkpoints;
    }


}
