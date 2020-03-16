package Stratego.View.GameScreen;

import Stratego.Model.gamePlay.army.ArmyColor;
import Stratego.Model.gamePlay.army.RankType;

public class ArmyRank {

    private ArmyColor armyColor;
    private RankType rankType;


    public ArmyRank(RankType rankType, ArmyColor armyColor) {
        this.armyColor = armyColor;
        this.rankType = rankType;
    }

    public ArmyColor getArmyColor() {
        return armyColor;
    }

    public RankType getRankType() {
        return rankType;
    }
}
