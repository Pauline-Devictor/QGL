package fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal;

import lombok.Getter;

public abstract class Goal {
    @Getter
    String mode;

    protected Goal(String mode){
            this.mode = mode;
    }
}
