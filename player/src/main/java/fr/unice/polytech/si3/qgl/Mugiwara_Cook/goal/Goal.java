package fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal;

abstract public class Goal {
    String mode;

    public Goal(String mode){
            this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}