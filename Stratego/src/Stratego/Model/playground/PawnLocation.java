package Stratego.Model.playground;


import Stratego.Model.army.Pawn;

public class PawnLocation extends Location {

    Pawn standOn;
    protected  boolean isInRange;


    public boolean isInRange() {
        return isInRange;
    }

    public void setInRange(boolean inRange) {
        isInRange = inRange;
    }


    public Pawn getStandOn() {
        return standOn;
    }

    public void setStandOn(Pawn standOn) {
        this.standOn = standOn;
    }
}
