package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import lombok.Getter;
import lombok.Setter;

abstract public class Action {
    @Getter
    @Setter
    String type;

    public Action(String type) {
        this.type = type;
    }
}
