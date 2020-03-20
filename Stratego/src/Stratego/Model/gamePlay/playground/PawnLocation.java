package Stratego.Model.gamePlay.playground;

import Stratego.Model.gamePlay.army.Pawn;

public class PawnLocation extends Location {

    public Pawn standOn;
    private boolean isInRange;
    private int x;
    private  int y;

    public PawnLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
