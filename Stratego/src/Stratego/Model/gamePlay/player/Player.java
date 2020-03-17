package Stratego.Model.gamePlay.player;

import Stratego.Model.gamePlay.Stratego;
import Stratego.Model.gamePlay.army.Army;
import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.Pawn;
import Stratego.Model.gamePlay.army.RankType;
import Stratego.Model.gamePlay.playground.Location;
import Stratego.Model.gamePlay.playground.PawnLocation;
import Stratego.Model.gamePlay.playground.Playground;


public class Player {

    protected Army army;
    private String name;
    private Pawn activePawn;

    public Player(String name, ArmyColor color) {
        this.army = new Army(color);
        this.name = name;
    }

    public Army getArmy() {
        return army;
    }

    public String getName() {
        return name;
    }

    public Pawn getActivePawn() {
        return activePawn;
    }

    public void take(PawnLocation pawn, Playground playground, Stratego game) throws Exception {
        playground.calcPossibleLocations(pawn);

        boolean AreInRange = false;
        for (Location[] loction : playground.getLocations()) {
            for (Location loc : loction) {
                if (loc instanceof PawnLocation) {
                    if (((PawnLocation) loc).isInRange()) {
                        AreInRange = true;
                    }
                }
            }

        }
        if (pawn.getStandOn().getParent().getColor() != game.getActivePlayer().getArmy().getColor()) {
            throw new Exception("He is not in your'e team!");
        } else if (pawn.getStandOn().getRank() == RankType.Flag || pawn.getStandOn().getRank() == RankType.Bomb) {
            throw new Exception("He can't move!");
        } else if (!AreInRange) {
            throw new Exception("He has no possible location to move to!");
        } else {
            this.activePawn = pawn.getStandOn();

        }
    }

    public void place(PawnLocation pawnLocation, Playground playground) throws Exception {
        if (pawnLocation.isInRange()) {
            if (pawnLocation.getStandOn() != null) {
                playground.fight(activePawn, pawnLocation.getStandOn());
            } else activePawn.move(pawnLocation);
        } else throw new Exception("Location is not in range!");
    }

}
