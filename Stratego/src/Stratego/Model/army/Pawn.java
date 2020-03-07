package Stratego.Model.army;


import Stratego.Model.playground.Location;
import Stratego.Model.playground.PawnLocation;

public class Pawn {

    protected Army parent;
    protected RankType rank;
    protected PawnLocation location;
   protected MoveType move;



    public Pawn( Army parent, RankType rank,MoveType move) {
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
        return location != null;
    }

    public void move(PawnLocation to) {
        this.location.setStandOn(null);
        this.location = to;
        this.location.setStandOn(this);

    }
}
