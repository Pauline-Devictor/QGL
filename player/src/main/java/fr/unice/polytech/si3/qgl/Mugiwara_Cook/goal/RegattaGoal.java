package fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import lombok.Getter;
import lombok.Setter;

public class RegattaGoal extends Goal {
    public static final String regatta = "REGATTA";
    @Getter
    @Setter
    public Checkpoint[] checkpoints;

    public RegattaGoal(Checkpoint[] checkpoints) {
        super(regatta);
        this.checkpoints = checkpoints;
    }
}
