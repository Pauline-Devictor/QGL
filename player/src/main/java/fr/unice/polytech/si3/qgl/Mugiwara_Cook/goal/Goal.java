package fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import lombok.Getter;
import lombok.Setter;

abstract public class Goal {
    @Getter
    @Setter
    String mode;

    public Goal(String mode){
            this.mode = mode;
    }
}
