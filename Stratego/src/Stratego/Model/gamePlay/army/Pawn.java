package Stratego.Model.gamePlay.army;

import Stratego.Model.gamePlay.playground.Location;
import Stratego.Model.gamePlay.playground.PawnLocation;

public class Pawn {
    //een pion
    private Army parent;
    private RankType rank;
    private PawnLocation location;
    private MoveType move;

    Pawn(Army parent, RankType rank, MoveType move) {
        this.parent = parent;
        this.rank = rank;
        this.move = move;
    }


    public Army getParent() {
        return parent;
    }

    public MoveType getMove() {
        return move;
    }

    public RankType getRank() {
        return rank;
    }

    public void setLocation(
            PawnLocation location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isSet() {
        //is gezet?
        return location != null;
    }

    public void move(PawnLocation to) {
        //verplaats pion
        this.location.setStandOn(null);
        this.location = to;
        this.location.setStandOn(this);

    }
}
