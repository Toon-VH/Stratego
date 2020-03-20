package Stratego.Model.gameAI;

import Stratego.Model.gamePlay.playground.PawnLocation;

public class AIMove {

    private PawnLocation soldier;
    private PawnLocation target;

    public AIMove(PawnLocation soldier, PawnLocation target) {
        this.soldier = soldier;
        this.target = target;
    }

    public PawnLocation getSoldier() {
        return soldier;
    }

    public PawnLocation getTarget() {
        return target;
    }
}
