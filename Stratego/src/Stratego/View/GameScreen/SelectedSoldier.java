package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.playground.PawnLocation;

public class SelectedSoldier {




    private PawnLocation selected;
    private int x;
    private int y;

    public SelectedSoldier( int x, int y,PawnLocation selected) {
        this.selected = selected;
        this.x = x;
        this.y = y;
    }

    public SelectedSoldier() {
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PawnLocation getFrom() {
        return selected;
    }

    public void setSelected(PawnLocation selected) {
        this.selected = selected;
    }
}
