package fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import lombok.Getter;
import lombok.Setter;

public class RegattaGoal extends Goal {
    public final static String MODE = "REGATTA";
    public String mode = MODE;
    @Getter
    @Setter
    public Checkpoint[] checkpoints;

    public RegattaGoal(Checkpoint[] checkpoints) {
        super(MODE);
        this.checkpoints = checkpoints;
    }
}
